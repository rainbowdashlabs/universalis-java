/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.chojo.universalis.exceptions.ErrorResponseException;
import de.chojo.universalis.exceptions.RequestException;
import de.chojo.universalis.exceptions.ResponseException;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.rest.requests.Buckets;
import de.chojo.universalis.rest.requests.Mapper;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.rest.routes.api.MarketableRequest;
import de.chojo.universalis.rest.routes.api.WorldsRequest;
import de.chojo.universalis.rest.routes.api.history.BlankHistoryRequest;
import de.chojo.universalis.rest.routes.requests.DataCentersRequestImpl;
import de.chojo.universalis.rest.routes.requests.HistoryRequestImpl;
import de.chojo.universalis.rest.routes.requests.MarketBoardRequestImpl;
import de.chojo.universalis.rest.routes.requests.MarketableRequestImpl;
import de.chojo.universalis.rest.routes.requests.TaxRatesRequestImpl;
import de.chojo.universalis.rest.routes.requests.WorldsRequestImpl;
import de.chojo.universalis.rest.routes.requests.extra.Extra;
import io.github.bucket4j.Bucket;
import org.apache.hc.core5.net.URIBuilder;
import org.jetbrains.annotations.CheckReturnValue;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ScheduledExecutorService;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Implementation of an universalis rest client
 */
public class UniversalisRestImpl implements UniversalisRest {
    private static final Logger log = getLogger(UniversalisRestImpl.class);
    private final Bucket xivapi = Buckets.newUniversalisBucket();
    private final HttpClient http;// = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private final ObjectMapper objectMapper;
    private final ScheduledExecutorService executorService;// = Executors.newScheduledThreadPool(2);
    private final Extra extra;

    /**
     * Create a new universalis rest client
     *
     * @param http             http client
     * @param executorService  executor service
     * @param itemNameSupplier item name supplier
     */
    public UniversalisRestImpl(HttpClient http, ScheduledExecutorService executorService, NameSupplier itemNameSupplier) {
        this.http = http;
        this.executorService = executorService;
        this.objectMapper = Mapper.create(itemNameSupplier);
        this.extra = new Extra(this);
    }

    /**
     * Get the http client
     *
     * @return http client
     */
    public HttpClient http() {
        return http;
    }

    /**
     * Get a pre configured uri builder
     *
     * @return new uri builder
     */
    public URIBuilder uri() {
        return new URIBuilder().setScheme("https").setHost("universalis.app").appendPathSegments("api", "v2");
    }

    /**
     * Get the object mapper
     *
     * @return object mapper
     */
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest marketBoard() {
        return new MarketBoardRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public WorldsRequest worlds() {
        return new WorldsRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public DataCentersRequest dataCenters() {
        return new DataCentersRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public BlankHistoryRequest history() {
        return new HistoryRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public TaxRatesRequestImpl taxRates() {
        return new TaxRatesRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public MarketableRequest marketable() {
        return new MarketableRequestImpl(this);
    }

    @Override
    @CheckReturnValue
    public Extra extra() {
        return extra;
    }

    /**
     * Send an asynchronous request to the uri and map the result body
     *
     * @param uri    uri
     * @param result result class
     * @param <T>    result type
     * @return future with result
     */
    public <T> CompletableFuture<T> getAsyncAndMap(URIBuilder uri, Class<T> result) {
        try {
            return getAsyncAndMap(uri.build(), result);
        } catch (URISyntaxException e) {
            throw new RequestException("Error while parsing URI", e);
        }
    }

    /**
     * Send a request and map the result body
     *
     * @param uri    uri
     * @param result result class
     * @param <T>    result type
     * @return result
     */
    public <T> T getAndMap(URIBuilder uri, Class<T> result) {
        try {
            return getAndMap(uri.build(), result);
        } catch (URISyntaxException e) {
            throw new RequestException("Error while parsing URI", e);
        }
    }

    /**
     * Send an asynchronous request to the uri and map the result body
     *
     * @param uri    uri
     * @param result result class
     * @param <T>    result type
     * @return future with result
     */
    public <T> CompletableFuture<T> getAsyncAndMap(URI uri, Class<T> result) {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return getAsyncAndMap(request, result);
    }

    /**
     * Send a request and map the result body
     *
     * @param uri    uri
     * @param result result class
     * @param <T>    result type
     * @return result
     */
    public <T> T getAndMap(URI uri, Class<T> result) {
        HttpRequest request = HttpRequest.newBuilder(uri).GET().build();
        return getAndMap(request, result);
    }

    /**
     * Send an asynchronous request and map the result body
     *
     * @param request request
     * @param result  result class
     * @param <T>     result type
     * @return future with result
     */
    public <T> CompletableFuture<T> getAsyncAndMap(HttpRequest request, Class<T> result) {
        return xivapi.asScheduler().consume(1, executorService)
                .thenApplyAsync(v -> getAndMapInternal(request, result), executorService);
    }

    /**
     * Send a request and map the result body
     *
     * @param request request
     * @param result  result class
     * @param <T>     result type
     * @return result
     */
    public <T> T getAndMap(HttpRequest request, Class<T> result) {
        try {
            xivapi.asBlocking().consume(1);
        } catch (InterruptedException e) {
            throw new RequestException("Interrupted while waiting for token", e);
        }
        return getAndMapInternal(request, result);
    }

    private <T> T getAndMapInternal(HttpRequest request, Class<T> result) {
        HttpResponse<String> response;
        try {
            log.trace("Requesting {}", request.uri());
            response = http().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException | IOException e) {
            throw new ResponseException("Error during reading the request", e);
        }

        try {
            if (response.statusCode() >= 400) {
                log.error("Received error {} during request: {}", response.statusCode(), response.body());
                throw new ErrorResponseException(response.statusCode(), response.body());
            }
            log.trace("Received\n{}", response.body());
            return objectMapper().readValue(response.body(), result);
        } catch (IOException e) {
            throw new ResponseException("Error during request mapping", e);
        }
    }
}
