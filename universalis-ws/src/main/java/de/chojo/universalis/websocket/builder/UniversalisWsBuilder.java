/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.builder;

import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.websocket.UniversalisWs;
import de.chojo.universalis.websocket.UniversalisWsImpl;
import de.chojo.universalis.websocket.subscriber.Subscription;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Builder to create a {@link UniversalisWs} instance.
 */
public class UniversalisWsBuilder {
    /**
     * Default websocket URL for the universalis service.
     */
    public static final String DEFAULT_WEBSOCKET_URL = "wss://universalis.app/api/ws";

    private final WebSocketFactory factory;
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final List<EventListener> listeners = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool(r -> {
        Thread t = new Thread(r, "universalis-ws-worker");
        t.setDaemon(true);
        return t;
    });
    private NameSupplier nameSupplier = NameSupplier.EMPTY;
    private String websocketUrl = DEFAULT_WEBSOCKET_URL;

    /**
     * Create a new universalis websocket builder
     *
     * @param factory factory to create a websocket
     */
    public UniversalisWsBuilder(WebSocketFactory factory) {
        this.factory = factory;
    }

    /**
     * Registers a listener.
     *
     * @param eventListener event listener
     * @return builder instance
     */
    public UniversalisWsBuilder registerListener(EventListener... eventListener) {
        Collections.addAll(listeners, eventListener);
        return this;
    }

    /**
     * Add a subscription for a channel.
     *
     * @param subscription subscription to add
     * @return builder instance
     */
    public UniversalisWsBuilder subscribe(Subscription subscription) {
        subscriptions.add(subscription);
        return this;
    }

    /**
     * Set the thread pool used to handle websocket events
     *
     * @param executorService executor service
     * @return builder instance
     */
    public UniversalisWsBuilder eventThreadPool(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    /**
     * Name supplier for item names. If this is not set item names will be blank.
     *
     * @param nameSupplier name supplier for items
     * @return builder instance
     */
    public UniversalisWsBuilder itemNameSupplier(NameSupplier nameSupplier) {
        this.nameSupplier = nameSupplier;
        return this;
    }

    /**
     * Override the websocket URL. Defaults to {@link #DEFAULT_WEBSOCKET_URL}.
     *
     * @param websocketUrl websocket url
     * @return builder instance
     */
    public UniversalisWsBuilder websocketUrl(String websocketUrl) {
        this.websocketUrl = websocketUrl;
        return this;
    }

    /**
     * Builds and attempts to connect the websocket.
     * <p>
     * Use {@link UniversalisWs#awaitReady()} to wait until the socket is connected.
     *
     * @return Universalis websocket instance
     */
    public UniversalisWs build() {
        UniversalisWsImpl universalisWs = new UniversalisWsImpl(factory, executorService, subscriptions, listeners, nameSupplier, websocketUrl);
        universalisWs.ignite();
        return universalisWs;
    }
}
