/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.connection.listener.StatusListener;
import de.chojo.universalis.connection.listener.WebsocketListenerAdapter;
import de.chojo.universalis.items.NameSupplier;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.subscriber.Subscription;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.slf4j.LoggerFactory.getLogger;

public class UniversalisWs {
    private static final Logger log = getLogger(UniversalisWs.class);
    private static final String WEBSOCKET_URL = "wss://universalis.app/api/ws";
    private final WebSocketFactory factory;
    private final ExecutorService websocketWorker;
    private final List<Subscription> subscribers;
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private WebSocket socket;
    private StatusListener statusListener;
    private boolean active = true;

    public UniversalisWs(WebSocketFactory factory, ExecutorService websocketWorker, List<Subscription> subscribers, List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.factory = factory;
        this.websocketWorker = websocketWorker;
        this.subscribers = subscribers;
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
    }


    public void ignite() throws IOException {
        if (!active) return;

        if (socket != null) {
            disconnect();
            log.info("Trying to reconnect");
        }

        socket = factory.createSocket(WEBSOCKET_URL);

        socket.setPingInterval(2000);

        socket.addListener(new WebsocketListenerAdapter(listeners, itemNameSupplier));
        statusListener = new StatusListener(this, subscribers);
        socket.addListener(statusListener);

        socket.connect(websocketWorker);
    }

    public void subscribe(Subscription subscription) {
        statusListener.subscibe(subscription);
    }

    public void unsubscrybe(Subscription subscription) {
        statusListener.unsubscribe(subscription);
    }

    public ExecutorService websocketWorker() {
        return websocketWorker;
    }

    public void disconnect() {
        active = false;
        socket.disconnect();
    }

    public WebSocket socket() {
        return socket;
    }

    /**
     * Suspends the thread until the websocket is connected.
     */
    public void awaitReady() {
        while (!statusListener.isConnected()) {
            Thread.onSpinWait();
        }
    }
}
