/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.websocket.events.concrete.listing.WsListingEvent;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * A websocket listings add event.
 */
public class WsListingAddEvent extends WsListingEvent<ListingAddEvent> {
    /**
     * Create a new websocket listing add event
     * @param item item
     * @param world world
     * @param listings listings
     */
    public WsListingAddEvent(@JsonProperty("item") Item item,
                             @JsonProperty("world") World world,
                             @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    @Override
    public ListingAddEvent toEvent() {
        return new ListingAddEvent(item(), world(), asListing(world()));
    }
}
