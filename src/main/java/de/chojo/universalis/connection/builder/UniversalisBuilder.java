/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.builder;

import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.connection.UniversalisWs;
import de.chojo.universalis.items.Items;
import de.chojo.universalis.items.NameSupplier;
import de.chojo.universalis.subscriber.Subscription;
import de.chojo.universalis.listener.EventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UniversalisBuilder {
    private final WebSocketFactory factory;
    private final List<Subscription> subscriptions = new ArrayList<>();
    private ExecutorService executorService = Executors.newCachedThreadPool();

    private final List<EventListener> listeners = new ArrayList<>();
    private NameSupplier nameSupplier;

    public UniversalisBuilder(WebSocketFactory factory) {
        this.factory = factory;
    }

    public UniversalisBuilder registerListener(EventListener... eventListener) {
        Collections.addAll(listeners, eventListener);
        return this;
    }

    public UniversalisBuilder subscribe(Subscription subscription) {
        subscriptions.add(subscription);
        return this;
    }

    public UniversalisBuilder eventThreadPool(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public UniversalisBuilder itemNameSupplier(NameSupplier nameSupplier){
        this.nameSupplier = nameSupplier;
        return this;
    }

    public UniversalisWs build() throws WebSocketException, IOException, InterruptedException {
        if(nameSupplier == null){
            nameSupplier = Items.create();
        }
        UniversalisWs universalisWs = new UniversalisWs(factory, executorService, subscriptions, listeners,nameSupplier );
        universalisWs.ignite();
        return universalisWs;
    }
}
