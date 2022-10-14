/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import org.apache.hc.core5.net.URIBuilder;
import org.slf4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static org.slf4j.LoggerFactory.getLogger;

public class RequestBuilder<T> implements Request<T> {
    private static final Logger log = getLogger(RequestBuilder.class);
    protected final UniversalisRestImpl rest;
    private final URIBuilder uriBuilder;
    private final Class<T> result;
    private final Consumer<T> postRetrievalHook;

    public RequestBuilder(UniversalisRestImpl rest, Class<T> result) {
        this(rest, result, r -> {
        });
    }

    public RequestBuilder(UniversalisRestImpl rest, Class<T> result, Consumer<T> postRetrievalHook) {
        this.rest = rest;
        uriBuilder = rest.uri();
        this.result = result;
        this.postRetrievalHook = postRetrievalHook;
    }

    public URIBuilder uriBuilder() {
        return uriBuilder;
    }

    public void parameter(String key, Object object) {
        uriBuilder.addParameter(key, String.valueOf(object));
    }

    public void path(String... path) {
        uriBuilder.appendPathSegments(path);
    }

    public void path(Object... path) {
        for (Object o : path) {
            uriBuilder.appendPath(String.valueOf(o));
        }
    }

    protected URI uri() {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<T> queue() {
        return rest.getAsyncAndMap(uri(), this.result)
                   .thenApply(res -> {
                       postRetrievalHook.accept(res);
                       return res;
                   });
    }

    @Override
    public T complete() {
        T result = rest.getAndMap(uri(), this.result);
        postRetrievalHook.accept(result);
        return result;
    }
}
