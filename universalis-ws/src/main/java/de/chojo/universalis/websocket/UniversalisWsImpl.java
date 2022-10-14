/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.websocket.subscriber.Subscription;
import de.chojo.universalis.websocket.listener.StatusListener;
import de.chojo.universalis.websocket.listener.WebsocketListenerAdapter;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

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
     *
     * @throws IOException Failed to create a socket. Or, HTTP proxy handshake or SSL handshake failed.
     */
    public void ignite() throws IOException {
        if (!active) return;

        if (socket != null) {
            disconnect();
            log.info("Trying to reconnect");
        }

        socket = factory.createSocket(WEBSOCKET_URL);

        socket.setPingInterval(factory.getSocketTimeout() / 4);

        socket.addListener(new WebsocketListenerAdapter(listeners, itemNameSupplier));
        statusListener = new StatusListener(this, subscribers);
        socket.addListener(statusListener);

        socket.connect(websocketWorker);
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
        socket.disconnect();
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
