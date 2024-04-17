/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket;

import de.chojo.universalis.websocket.subscriber.Subscription;
import de.chojo.universalis.websocket.builder.UniversalisWsBuilder;
import de.chojo.universalis.websocket.builder.WebsocketFactoryBuilder;

/**
 * Interface for universalis websocket implementation.
 */
public interface UniversalisWs {
    /**
     * Add a subscription.
     *
     * @param subscription subcription to add
     */
    void subscribe(Subscription subscription);

    /**
     * Remove a subscription.
     *
     * @param subscription subscription to remove
     */
    void unsubscribe(Subscription subscription);

    /**
     * Disconnect the websocket. Once disconnected the socket can no longer be used.
     */
    void disconnect();

    /**
     * Suspends the thread until the websocket is connected.
     */
    void awaitReady();

    /**
     * Get a websocket builder. Use this if you want more control about the socket itself.
     * <p>
     * Use {@link #getDefault()} if your don't know that this means.
     *
     * @return websocket factory builder
     */
    static WebsocketFactoryBuilder websocketBuilder() {
        return new WebsocketFactoryBuilder();
    }

    /**
     * Get a universalis builder for websockets.
     *
     * @return universalis websocket builder
     */
    static UniversalisWsBuilder getDefault() {
        return new WebsocketFactoryBuilder().setConnectionTimeout(10000).build();
    }
}
