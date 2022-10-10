/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.listing.WsListingBase;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingAdd;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

public class WsListingAdd extends WsListingBase {
    public WsListingAdd(@JsonProperty("item") int item,
                        @JsonProperty("world") int world,
                        @JsonProperty("listings") List<ListingView> listings) {
        super(item, world, listings);
    }

    public ListingAdd toEvent(NameSupplier items) {
        return new ListingAdd(item(items), world(), asListing(world()));
    }
}
