/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing;

import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.Event;

import java.util.List;

/**
 * A websocket listing event.
 */
public abstract class WsListingEvent<T extends Event> extends WsEvent<T> {
    private final List<ListingView> listings;

    public WsListingEvent(int item, int world, List<ListingView> listings) {
        super(item, world);
        this.listings = listings;
    }

    /**
     * The listing views contained in this event.
     *
     * @return unmodifiable list
     */
    public List<ListingView> listings() {
        return listings;
    }

    /**
     * Converts the {@link ListingView} to a {@link Listing} object.
     *
     * @param world world for the sale
     * @return list of sales
     */
    public List<Listing> asListing(World world) {
        return listings().stream().map(view -> view.toListing(world)).toList();
    }
}
