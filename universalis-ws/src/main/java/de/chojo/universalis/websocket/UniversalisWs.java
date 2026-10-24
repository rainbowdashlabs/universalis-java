/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket;

import de.chojo.universalis.websocket.builder.UniversalisWsBuilder;
import de.chojo.universalis.websocket.builder.WebsocketFactoryBuilder;
import de.chojo.universalis.websocket.subscriber.Subscription;

import java.time.Duration;

/**
 * Interface for universalis websocket implementation.
 */
public interface UniversalisWs {
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

    /**
     * Add a subscription. It is sent right away when the websocket is connected, and again after every reconnect.
     *
     * @param subscription subscription to add
     */
    void subscribe(Subscription subscription);

    /**
     * Remove a subscription. It is no longer sent after a reconnect.
     *
     * @param subscription subscription to remove
     */
    void unsubscribe(Subscription subscription);

    /**
     * Disconnect the websocket. Once disconnected the socket can no longer be used.
     */
    void disconnect();

    /**
     * Blocks until the websocket is connected. Returns early, with the interrupt flag set, when the thread is
     * interrupted.
     */
    void awaitReady();

    /**
     * Blocks until the websocket is connected or the timeout has passed.
     *
     * @param timeout the longest time to wait
     * @return true if the websocket is connected, false if the timeout passed or the thread was interrupted
     */
    boolean awaitReady(Duration timeout);
}
