/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests;

import de.chojo.universalis.exceptions.RequestException;
import de.chojo.universalis.rest.UniversalisRestImpl;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * A request builder used to build, execute requests and parse the response
 *
 * @param <T> type of response
 */
public class RequestBuilder<T> implements Request<T> {
    private static final Logger log = getLogger(RequestBuilder.class);
    protected final UniversalisRestImpl rest;
    private final URIBuilder uriBuilder;
    private final Class<T> result;
    private final Consumer<T> postRetrievalHook;

    /**
     * Create a new request builder
     *
     * @param rest   rest client
     * @param result result of the request
     */
    public RequestBuilder(UniversalisRestImpl rest, Class<T> result) {
        this(rest, result, r -> {
        });
    }

    /**
     * Creates a new request builder
     *
     * @param rest              rest client
     * @param result            result of the request
     * @param postRetrievalHook modification of the result
     */
    public RequestBuilder(UniversalisRestImpl rest, Class<T> result, Consumer<T> postRetrievalHook) {
        this.rest = rest;
        uriBuilder = rest.uri();
        this.result = result;
        this.postRetrievalHook = postRetrievalHook;
    }

    private URIBuilder uriBuilder() {
        return uriBuilder;
    }

    /**
     * Adds a parameter to the uri
     *
     * @param key    parameter key
     * @param object parameter value
     */
    public void parameter(String key, Object object) {
        uriBuilder.removeParameter(key);
        uriBuilder.addParameter(key, String.valueOf(object));
    }

    /**
     * Adds an element to the path.
     *
     * @param path path
     */
    public void path(String... path) {
        uriBuilder.appendPathSegments(path);
    }

    /**
     * Adds an element to the path.
     *
     * @param path path
     */
    public void path(Object... path) {
        for (Object o : path) {
            uriBuilder.appendPath(String.valueOf(o));
        }
    }

    private URI uri() {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RequestException("Could not build URI", e);
        }
    }

    @Override
    public CompletableFuture<T> queue() {
        return rest.getAsyncAndMap(uriBuilder, this.result)
                   .thenApply(res -> {
                       postRetrievalHook.accept(res);
                       return res;
                   });
    }

    @Override
    public T complete() {
        T result = rest.getAndMap(uriBuilder, this.result);
        postRetrievalHook.accept(result);
        return result;
    }

    @Override
    public String toString() {
        return "%s: %s".formatted(getClass().getSimpleName(), uri());
    }
}
