/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.listing.WsListingEvent;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

/**
 * A websocket listings add event.
 */
public class WsListingAddEvent extends WsListingEvent<ListingAddEvent> {
    public WsListingAddEvent(@JsonProperty("item") int item,
                             @JsonProperty("world") int world,
                             @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    @Override
    public ListingAddEvent toEvent(NameSupplier items) {
        return new ListingAddEvent(item(items), world(), asListing(world()));
    }
}
