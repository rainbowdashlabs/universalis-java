/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing.impl;

import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

/**
 * The listing update. Created by merging a {@link WsListingRemoveEvent} with a {@link WsListingAddEvent}
 */
public class WsListingUpdateEvent extends WsEvent<ListingUpdateEvent> {
    private final List<ListingView> added;
    private final List<ListingView> removed;

    public WsListingUpdateEvent(int item, int world, List<ListingView> added, List<ListingView> removed) {
        super(item, world);
        this.added = added;
        this.removed = removed;
    }

    /**
     * The added entries in this update
     *
     * @return list of listings
     */
    public List<ListingView> added() {
        return added;
    }

    /**
     * The removed entries in this update
     *
     * @return list of listings
     */
    public List<ListingView> removed() {
        return removed;
    }

    /**
     * Converts the event to a listing update event
     *
     * @param nameSupplier item name supplier
     * @return listing update event
     */
    @Override
    public ListingUpdateEvent toEvent(NameSupplier nameSupplier) {
        World world = world();
        List<Listing> added = this.added.stream().map(view -> view.toListing(world)).toList();
        List<Listing> removed = this.removed.stream().map(view -> view.toListing(world)).toList();
        return new ListingUpdateEvent(item(nameSupplier), world, added, removed);
    }
}
