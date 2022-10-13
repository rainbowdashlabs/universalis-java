/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.chojo.universalis.entities.Name;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.provider.items.Items;
import de.chojo.universalis.rest.requests.Buckets;
import de.chojo.universalis.rest.requests.Mapper;
import de.chojo.universalis.rest.routes.HistoryRequestImpl;
import de.chojo.universalis.rest.routes.TaxRatesRequestImpl;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;
import de.chojo.universalis.rest.routes.DataCentersRequestImpl;
import de.chojo.universalis.rest.routes.MarketBoardRequestImpl;
import de.chojo.universalis.rest.routes.api.WorldsRequest;
import de.chojo.universalis.rest.routes.WorldsRequestImpl;
import de.chojo.universalis.rest.routes.api.history.BlankHistoryRequest;
import io.github.bucket4j.Bucket;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static org.slf4j.LoggerFactory.getLogger;

public class UniversalisRestImpl implements UniversalisRest {
    private final Bucket xivapi = Buckets.newUniversalisBucket();
    private static final Logger log = getLogger(UniversalisRestImpl.class);
    private final HttpClient http = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper objectMapper;
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);


    public UniversalisRestImpl() {
        NameSupplier items = id -> new Name("", "", "", "");
        try {
            items = Items.create();
        } catch (IOException | InterruptedException ignored) {
        }
        objectMapper = Mapper.create(items);
    }

    public HttpClient http() {
        return http;
    }

    public URIBuilder uri() {
        return new URIBuilder().setScheme("https").setHost("universalis.app").appendPathSegments("api", "v2");
    }

    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    @Override
    public MarketBoardRequestImpl marketBoard() {
        return new MarketBoardRequestImpl(this);
    }

    @Override
    public WorldsRequest worlds() {
        return new WorldsRequestImpl(this);
    }

    @Override
    public DataCentersRequest dataCenters() {
        return new DataCentersRequestImpl(this);
    }

    @Override
    public BlankHistoryRequest history() {
        return new HistoryRequestImpl(this);
    }

    @Override
    public TaxRatesRequestImpl taxRates() {
        return new TaxRatesRequestImpl(this);
    }

    public <T> CompletableFuture<T> getAsyncAndMap(URIBuilder uri, Class<T> result) {
        try {
            return getAsyncAndMap(uri.build(), result);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getAndMap(URIBuilder uri, Class<T> result) {
        try {
            return getAndMap(uri.build(), result);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> CompletableFuture<T> getAsyncAndMap(URI uri, Class<T> result) {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return getAsyncAndMap(request, result);
    }

    public <T> T getAndMap(URI uri, Class<T> result) {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return getAndMap(request, result);
    }

    public <T> CompletableFuture<T> getAsyncAndMap(HttpRequest request, Class<T> result) {
        return xivapi.asScheduler().consume(1, executorService)
                     .thenApplyAsync(v -> getAndMapInternal(request, result), executorService);
    }

    public <T> T getAndMap(HttpRequest request, Class<T> result) {
        try {
            xivapi.asBlocking().consume(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getAndMapInternal(request, result);
    }

    private <T> T getAndMapInternal(HttpRequest request, Class<T> result) {
        try {
            log.trace("Requesting {}", request.uri());
            System.out.println(request.uri().toString());
            HttpResponse<String> response = http().send(request, HttpResponse.BodyHandlers.ofString());
            log.trace("Received\n{}", response.body());
            System.out.println(response.body());
            return objectMapper().readValue(response.body(), result);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
