/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing;

import de.chojo.universalis.connection.events.BaseEvent;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.worlds.World;

import java.util.List;

public class WsListingBase extends BaseEvent {
    private final List<ListingView> listings;

    public WsListingBase(int item, int world, List<ListingView> listings) {
        super(item, world);
        this.listings = listings;
    }

    public List<ListingView> listings() {
        return listings;
    }

    public List<Listing> asListing(World world) {
        return listings().stream().map(view -> view.toEvent(world)).toList();
    }
}
