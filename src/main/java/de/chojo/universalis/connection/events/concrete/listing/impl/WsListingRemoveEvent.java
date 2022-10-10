/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.listing.WsListingEvent;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.provider.NameSupplier;

import java.util.ArrayList;
import java.util.List;

/**
 * A websocket listings remove event.
 */
public class WsListingRemoveEvent extends WsListingEvent<ListingRemoveEvent> {
    public WsListingRemoveEvent(@JsonProperty("item") int item,
                                @JsonProperty("world") int world,
                                @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    @Override
    public ListingRemoveEvent toEvent(NameSupplier items) {
        return new ListingRemoveEvent(item(items), world(), asListing(world()));
    }

    /**
     * Creates an update event out of this remove event and an add event.
     * @param add add event
     * @return update event
     */
    public WsListingUpdateEvent toUpdate(WsListingAddEvent add) {
        int itemId = itemId();
        int worldId = worldId();
        List<ListingView> removed = new ArrayList<>(listings());
        List<ListingView> added = new ArrayList<>(add.listings());
        added.removeAll(listings());
        removed.removeAll(add.listings());
        return new WsListingUpdateEvent(itemId, worldId, added, removed);
    }
}
