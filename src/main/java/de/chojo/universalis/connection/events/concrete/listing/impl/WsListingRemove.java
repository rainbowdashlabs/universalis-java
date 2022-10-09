/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.listing.WsListingBase;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.events.listings.ListingRemove;
import de.chojo.universalis.items.NameSupplier;

import java.util.List;

public class WsListingRemove extends WsListingBase {
    public WsListingRemove(@JsonProperty("item") int item,
                           @JsonProperty("world") int world,
                           @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    public ListingRemove toEvent(NameSupplier items) {
        return new ListingRemove(item(items), world(), asListing(world()));
    }
}
