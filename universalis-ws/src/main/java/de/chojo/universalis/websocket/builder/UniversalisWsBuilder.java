/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.builder;

import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.websocket.UniversalisWs;
import de.chojo.universalis.websocket.UniversalisWsImpl;
import de.chojo.universalis.entities.Name;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.subscriber.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniversalisWsBuilder {
    private final WebSocketFactory factory;
    private final List<Subscription> subscriptions = new ArrayList<>();
    private final List<EventListener> listeners = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private NameSupplier nameSupplier = id -> new Name("", "", "", "");

    public UniversalisWsBuilder(WebSocketFactory factory) {
        this.factory = factory;
    }

    /**
     * Registers a listener.
     *
     * @param eventListener eventlistener
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
     * Builds and attempts to connect the websocket.
     * <p>
     * Use {@link UniversalisWs#awaitReady()} to wait until the socket is connected.
     *
     * @return Universalis websocket instance
     * @throws IOException Failed to create a socket. Or, HTTP proxy handshake or SSL handshake failed.
     */
    public UniversalisWs build() throws IOException {
        UniversalisWsImpl universalisWs = new UniversalisWsImpl(factory, executorService, subscriptions, listeners, nameSupplier);
        universalisWs.ignite();
        return universalisWs;
    }
}
