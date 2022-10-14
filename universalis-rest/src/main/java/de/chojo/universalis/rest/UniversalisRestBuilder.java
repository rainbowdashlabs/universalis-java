/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import de.chojo.universalis.entities.Name;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.provider.items.Items;

import javax.annotation.CheckReturnValue;
import java.net.http.HttpClient;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Class to build a universalis rest client
 */
public class UniversalisRestBuilder {
    private HttpClient http = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
    private NameSupplier nameSupplier = id -> new Name("", "", "", "");

    /**
     * Set the http client used to send requests to univeralis
     *
     * @param http client
     * @return builder
     */
    @CheckReturnValue
    public UniversalisRestBuilder setHttp(HttpClient http) {
        this.http = http;
        return this;
    }

    /**
     * Set the executor service used for asynchronous requests.
     *
     * @param executorService executor service
     * @return builder
     */
    @CheckReturnValue
    public UniversalisRestBuilder setExecutorService(ScheduledExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    /**
     * Set the item name supplier. This is required to get item names on runtime. Use {@link Items#create()} to get a default implementation using a fallback file.
     *
     * @param nameSupplier name supplier
     * @return builder
     */
    @CheckReturnValue
    public UniversalisRestBuilder setItemNameSupplier(NameSupplier nameSupplier) {
        this.nameSupplier = nameSupplier;
        return this;
    }

    /**
     * Build the api. The instance is ready to use.
     *
     * @return api instance
     */
    @CheckReturnValue
    public UniversalisRest build() {
        return new UniversalisRestImpl(http, executorService, nameSupplier);
    }
}
