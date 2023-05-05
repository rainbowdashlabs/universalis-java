/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.websocket.events.concrete.listing.WsListingEvent;
import de.chojo.universalis.worlds.World;

import java.util.ArrayList;
import java.util.List;

/**
 * A websocket listings remove event.
 */
public class WsListingRemoveEvent extends WsListingEvent<ListingRemoveEvent> {
    /**
     * Create a new websocket listing remove event
     *
     * @param item     item
     * @param world    world
     * @param listings listings
     */
    public WsListingRemoveEvent(@JsonProperty("item") Item item,
                                @JsonProperty("world") World world,
                                @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    @Override
    public ListingRemoveEvent toEvent() {
        return new ListingRemoveEvent(item(), world(), asListing(world()));
    }

    /**
     * Creates an update event out of this remove event and an add event.
     *
     * @param add add event
     * @return update event
     */
    public WsListingUpdateEvent toUpdate(WsListingAddEvent add) {
        Item itemId = item();
        World worldId = world();
        List<ListingView> removed = new ArrayList<>(listings());
        List<ListingView> added = new ArrayList<>(add.listings());
        added.removeAll(listings());
        removed.removeAll(add.listings());
        return new WsListingUpdateEvent(itemId, worldId, added, removed);
    }
}
