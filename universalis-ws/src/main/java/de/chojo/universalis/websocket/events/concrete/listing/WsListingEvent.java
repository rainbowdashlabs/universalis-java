/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing;

import de.chojo.universalis.entities.Item;
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

    /**
     * Create a new websocket listing event
     * @param item item id
     * @param world world id
     * @param listings listings
     */
    public WsListingEvent(Item item, World world, List<ListingView> listings) {
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
