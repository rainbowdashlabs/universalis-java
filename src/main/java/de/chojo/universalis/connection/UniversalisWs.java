/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection;

import de.chojo.universalis.subscriber.Subscription;

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
}
