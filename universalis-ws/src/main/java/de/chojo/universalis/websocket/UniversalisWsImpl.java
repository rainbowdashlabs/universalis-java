/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.websocket.listener.StatusListener;
import de.chojo.universalis.websocket.listener.WebsocketListenerAdapter;
import de.chojo.universalis.websocket.subscriber.Subscription;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Class to handle websocket connection to universalis.
 */
public class UniversalisWsImpl implements UniversalisWs {
    private static final Logger log = getLogger(UniversalisWsImpl.class);
    private static final Duration RECONNECT_DELAY = Duration.ofSeconds(5);
    private final String websocketUrl;
    private final WebSocketFactory factory;
    private final ExecutorService websocketWorker;
    private final List<Subscription> subscribers;
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private final AtomicBoolean reconnectInFlight = new AtomicBoolean();
    private volatile WebSocket socket;
    private volatile StatusListener statusListener;
    private volatile boolean active = true;

    /**
     * Creates an universalis websocket implementation.
     *
     * @param factory          factory for websocket creation
     * @param websocketWorker  worker for event handling
     * @param subscribers      subscriptions
     * @param listeners        listeners
     * @param itemNameSupplier item name supplier
     * @param websocketUrl     websocket url
     */
    public UniversalisWsImpl(WebSocketFactory factory, ExecutorService websocketWorker, List<Subscription> subscribers, List<EventListener> listeners, NameSupplier itemNameSupplier, String websocketUrl) {
        this.factory = factory;
        this.websocketWorker = websocketWorker;
        this.subscribers = subscribers;
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
        this.websocketUrl = websocketUrl;
    }


    /**
     * Attempts to create the socket and connect it
     */
    public void ignite() {
        if (!active) return;
        try {
            internalIgnite();
        } catch (Throwable e) {
            scheduleReconnect("Failed to create a socket", e);
        }
    }

    private void internalIgnite() {
        if (!active) return;

        WebSocket previous = socket;
        if (previous != null) {
            log.info("Closing previous socket");
            previous.clearListeners();
            if (previous.isOpen()) {
                previous.disconnect(0);
            }
        }

        WebSocket newSocket;
        try {
            log.info("Creating a new socket");
            newSocket = factory.createSocket(websocketUrl, 10000);
        } catch (IOException e) {
            scheduleReconnect("Failed to create a socket", e);
            return;
        }

        newSocket.setPingInterval(5);
        newSocket.addListener(new WebsocketListenerAdapter(listeners, itemNameSupplier));
        StatusListener listener = new StatusListener(this, subscribers);
        newSocket.addListener(listener);

        socket = newSocket;
        statusListener = listener;

        CompletableFuture.runAsync(() -> {
            try {
                log.info("Attempting to establish socket connection.");
                newSocket.connect();
            } catch (WebSocketException e) {
                scheduleReconnect("Failed to establish socket connection", e);
            }
        }, websocketWorker);
    }

    private void scheduleReconnect(String reason, Throwable cause) {
        if (!active) return;
        if (!reconnectInFlight.compareAndSet(false, true)) {
            log.debug("Reconnect already in flight, skipping ({})", reason);
            return;
        }
        log.error("{}. Trying again in {}s.", reason, RECONNECT_DELAY.toSeconds(), cause);
        CompletableFuture.delayedExecutor(RECONNECT_DELAY.toSeconds(), TimeUnit.SECONDS).execute(() -> {
            reconnectInFlight.set(false);
            ignite();
        });
    }

    @Override
    public void subscribe(Subscription subscription) {
        StatusListener listener = statusListener;
        if (listener == null) {
            throw new IllegalStateException("Websocket has not been ignited yet.");
        }
        listener.subscribe(subscription);
    }

    @Override
    public void unsubscribe(Subscription subscription) {
        StatusListener listener = statusListener;
        if (listener == null) {
            throw new IllegalStateException("Websocket has not been ignited yet.");
        }
        listener.unsubscribe(subscription);
    }

    @Override
    public void disconnect() {
        active = false;
        log.info("Attempting to disconnect socket");
        WebSocket current = socket;
        if (current != null) {
            current.clearListeners();
            current.disconnect(0);
        }
        log.info("Socket disconnected");
    }

    /**
     * The currently active socket
     *
     * @return socket
     */
    @Nullable
    public WebSocket socket() {
        return socket;
    }

    @Override
    public void awaitReady() {
        StatusListener listener;
        while ((listener = statusListener) == null || !listener.isConnected()) {
            Thread.onSpinWait();
        }
    }
}
