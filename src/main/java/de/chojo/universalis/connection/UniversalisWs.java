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
import de.chojo.universalis.subscriber.Subscription;
import de.chojo.universalis.listener.EventListener;
import org.bson.BSONDecoder;
import org.bson.BasicBSONDecoder;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;

import static org.slf4j.LoggerFactory.getLogger;

public class UniversalisWs {
    private static final Logger log = getLogger(UniversalisWs.class);
    private final BSONDecoder decoder = new BasicBSONDecoder();
    private final WebSocketFactory factory;
    private final ExecutorService websocketWorker;

    private final List<Subscription> subscribers;
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;

    public UniversalisWs(WebSocketFactory factory, ExecutorService websocketWorker, List<Subscription> subscribers, List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.factory = factory;
        this.websocketWorker = websocketWorker;
        this.subscribers = subscribers;
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
    }


    public void ignite() throws IOException {

        WebSocket ws = factory.createSocket("wss://universalis.app/api/ws");

        ws.setPingInterval(2000);

        ws.addListener(new WebsocketListenerAdapter(listeners, itemNameSupplier));
        ws.addListener(new StatusListener(subscribers));

        ws.connect(websocketWorker);
    }
}
