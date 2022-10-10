/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.listener;

import de.chojo.universalis.events.listings.impl.ListingAdd;
import de.chojo.universalis.events.listings.impl.ListingRemove;
import de.chojo.universalis.events.listings.impl.ListingUpdate;
import de.chojo.universalis.events.sales.impl.SalesAdd;
import de.chojo.universalis.events.sales.impl.SalesRemove;
import de.chojo.universalis.subscriber.Subscriptions;

public interface EventListener {
    /**
     * Called when a listing was added.
     *
     * @param event add event
     */
    void onListingAdd(ListingAdd event);

    /**
     * Called when a listing was removed. This usually happens right before a {@link #onListingAdd(ListingAdd)} call.
     *
     * @param event remove event
     */
    void onListingRemove(ListingRemove event);

    /**
     * Called when a listing was added after getting removed. This usually means it was updated.
     * <p>
     * This only works when {@link Subscriptions#listingAdd()} and {@link Subscriptions#listingRemove()} are subscribed for this world.
     * <p>
     * This event is created manually. It completely relies on universalis sending the remove event directly before the send event.
     *
     * @param event update event
     */
    void onListingUpdate(ListingUpdate event);

    /**
     * Called when sales were added.
     *
     * @param event add event
     */
    void onSalesAdd(SalesAdd event);

    /**
     * Called when sales were removed.
     *
     * @param event remove event
     * @deprecated Sales are no longer removed. Therefore, this event will not be called probably
     */
    @Deprecated
    void onSalesRemove(SalesRemove event);
}
