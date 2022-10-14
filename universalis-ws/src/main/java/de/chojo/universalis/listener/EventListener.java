/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.listener;

import de.chojo.universalis.websocket.UniversalisWs;
import de.chojo.universalis.websocket.builder.UniversalisWsBuilder;
import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;
import de.chojo.universalis.websocket.subscriber.Subscriptions;

/**
 * Class to register at a {@link UniversalisWsBuilder} or {@link UniversalisWs} to receive events.
 */
public interface EventListener {
    /**
     * Called when a listing was added.
     *
     * @param event add event
     */
    void onListingAdd(ListingAddEvent event);

    /**
     * Called when a listing was removed. This usually happens right before a {@link #onListingAdd(ListingAddEvent)} call.
     *
     * @param event remove event
     */
    void onListingRemove(ListingRemoveEvent event);

    /**
     * Called when a listing was added after getting removed. This usually means it was updated.
     * <p>
     * This only works when {@link Subscriptions#listingAdd()} and {@link Subscriptions#listingRemove()} are subscribed for this world.
     * <p>
     * This event is created manually. It completely relies on universalis sending the remove event directly before the send event.
     *
     * @param event update event
     */
    void onListingUpdate(ListingUpdateEvent event);

    /**
     * Called when sales were added.
     *
     * @param event add event
     */
    void onSalesAdd(SalesAddEvent event);

    /**
     * Called when sales were removed.
     *
     * @param event remove event
     * @deprecated Sales are no longer removed. Therefore, this event will not be called probably
     */
    @Deprecated
    void onSalesRemove(SalesRemoveEvent event);
}
