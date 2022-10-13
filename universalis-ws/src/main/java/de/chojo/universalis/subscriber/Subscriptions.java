/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.subscriber;

import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;

/**
 * Interface providing valid subscription channels
 */
public interface Subscriptions {
    /**
     * Gets a subscription for the listings/add route to receive {@link ListingAddEvent}s
     *
     * @return new subscription
     */
    static Subscription listingAdd() {
        return new Subscription("listings/add");
    }

    /**
     * Gets a subscription for the listings/remove route to receive {@link ListingRemoveEvent}s
     *
     * @return new subscription
     */
    static Subscription listingRemove() {
        return new Subscription("listings/remove");
    }

    /**
     * Gets a subscription for the sales/add route to receive {@link SalesAddEvent}s
     *
     * @return new subscription
     */
    static Subscription salesAdd() {
        return new Subscription("sales/add");
    }

    /**
     * Gets a subscription for the sales/remove route to receive {@link SalesRemoveEvent}s
     *
     * @return new subscription
     * @deprecated Sales do not get removed at the moment
     */
    @Deprecated
    static Subscription salesRemove() {
        return new Subscription("sales/remove");
    }
}
