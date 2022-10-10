/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.impl;

import de.chojo.universalis.connection.events.BaseEvent;
import de.chojo.universalis.connection.events.concrete.listing.WsListingBase;
import de.chojo.universalis.connection.events.concrete.listing.views.ListingView;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.shared.World;
import de.chojo.universalis.events.listings.impl.ListingUpdate;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

public class WsListingUpdate extends BaseEvent {
    private final List<ListingView> added;
    private final List<ListingView> removed;

    public WsListingUpdate(int item, int world, List<ListingView> added, List<ListingView> removed) {
        super(item, world);
        this.added = added;
        this.removed = removed;
    }

    public List<ListingView> added() {
        return added;
    }

    public List<ListingView> removed() {
        return removed;
    }

    public ListingUpdate toEvent(NameSupplier nameSupplier){
        World world = world();
        List<Listing> added = this.added.stream().map(view -> view.toEvent(world)).toList();
        List<Listing> removed = this.removed.stream().map(view -> view.toEvent(world)).toList();
        return new ListingUpdate(item(nameSupplier), world, added, removed);
    }
}
