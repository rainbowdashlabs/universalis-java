/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Class to handle websocket connection to universalis.
 */
public class UniversalisWsImpl implements UniversalisWs {
    private static final Logger log = getLogger(UniversalisWsImpl.class);
    private static final String WEBSOCKET_URL = "wss://universalis.app/api/ws";
    private final WebSocketFactory factory;
    private final ExecutorService websocketWorker;
    private final List<Subscription> subscribers;
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private WebSocket socket;
    private StatusListener statusListener;
    private boolean active = true;

    /**
     * Creates an universalis websocket implementation.
     *
     * @param factory          factory for websocket creation
     * @param websocketWorker  worker for event handling
     * @param subscribers      subscriptions
     * @param listeners        listeners
     * @param itemNameSupplier item name supplier
     */
    public UniversalisWsImpl(WebSocketFactory factory, ExecutorService websocketWorker, List<Subscription> subscribers, List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.factory = factory;
        this.websocketWorker = websocketWorker;
        this.subscribers = subscribers;
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
    }


    /**
     * Attempts to create the socket and connect it
     */
    public void ignite() {
        try {
            internalIgnite();
        } catch (Throwable e) {
            log.error("Failed to create a socket", e);
            log.info("Trying again in 5 seconds");
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(this::ignite);
        }
    }

    private void internalIgnite() {
        if (!active) return;

        if (socket != null) {
            log.info("Found old socket. Checking");
            if (socket.isOpen()) {
                log.info("Socket is open. Closing");
                socket.disconnect(0);
            }
            log.info("Trying to reconnect");
        }

        try {
            log.info("Creating a new socket");
            socket = factory.createSocket(WEBSOCKET_URL, 10000);
        } catch (IOException e) {
            log.error("Failed to create a socket", e);
            log.info("Trying again in 5 seconds");
            CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(this::ignite);
            return;
        }

        socket.setPingInterval(5);

        socket.addListener(new WebsocketListenerAdapter(listeners, itemNameSupplier));
        statusListener = new StatusListener(this, subscribers);
        socket.addListener(statusListener);

        CompletableFuture.runAsync(() -> {
            try {
                log.info("Attempting to establish socket connection.");
                socket.connect();
            } catch (WebSocketException e) {
                log.error("Failed to create a connection", e);
                log.info("Trying again in 5 seconds");
                CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(this::ignite);
            }
        }, websocketWorker);

    }

    @Override
    public void subscribe(Subscription subscription) {
        statusListener.subscibe(subscription);
    }

    @Override
    public void unsubscribe(Subscription subscription) {
        statusListener.unsubscribe(subscription);
    }

    @Override
    public void disconnect() {
        active = false;
        log.info("Attempting to disconnect socket");
        socket.disconnect(0);
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
        while (!statusListener.isConnected()) {
            Thread.onSpinWait();
        }
    }
}
