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
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
    private boolean hasScope;
    private boolean hasItem;

    /**
     * Create a new request builder
     *
     * @param rest   rest client
     * @param result result of the request
     */
    public RequestBuilder(UniversalisRestImpl rest, Class<T> result) {
        this(rest, result, r -> {});
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

    /**
     * Records that a top-level scope (world/dataCenter/region) has been chosen.
     * Throws if a scope is already set — request scopes are mutually exclusive.
     */
    public void markScope(String name) {
        if (hasScope) {
            throw new IllegalStateException(
                    "A scope has already been set on this request; scopes are mutually exclusive. Attempted to add: "
                            + name);
        }
        hasScope = true;
    }

    /**
     * Adds the item to the path. Universalis answers a request for several items with a different response, which the
     * response types of this library do not cover, so a request takes exactly one item.
     *
     * @param itemIds ids of the items to request
     * @throws IllegalArgumentException if not exactly one id is given
     * @throws IllegalStateException    if the item has already been set on this request
     */
    public void itemPath(Collection<Integer> itemIds) {
        if (itemIds.size() != 1) {
            throw new IllegalArgumentException(
                    "A request takes exactly one item, but " + itemIds.size() + " were given: " + itemIds);
        }
        if (hasItem) {
            throw new IllegalStateException("The item has already been set on this request.");
        }
        hasItem = true;
        path(itemIds.iterator().next());
    }

    /**
     * Adds several items to the path, separated by commas.
     *
     * @param itemIds ids of the items to request
     * @param max     the most items Universalis accepts in one request
     * @throws IllegalArgumentException if no id or more than {@code max} ids are given
     * @throws IllegalStateException    if the items have already been set on this request
     */
    public void itemsPath(Collection<Integer> itemIds, int max) {
        if (itemIds.isEmpty() || itemIds.size() > max) {
            throw new IllegalArgumentException(
                    "A request takes 1 to " + max + " items, but " + itemIds.size() + " were given.");
        }
        if (hasItem) {
            throw new IllegalStateException("The items have already been set on this request.");
        }
        hasItem = true;
        path(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
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

    @Override
    public CompletableFuture<T> queue() {
        return rest.getAsyncAndMap(uriBuilder, this.result).thenApply(res -> {
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

    private URIBuilder uriBuilder() {
        return uriBuilder;
    }

    private URI uri() {
        try {
            return uriBuilder.build();
        } catch (URISyntaxException e) {
            throw new RequestException("Could not build URI", e);
        }
    }
}
