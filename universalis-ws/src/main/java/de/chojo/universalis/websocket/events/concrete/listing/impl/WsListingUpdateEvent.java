/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.listing.impl;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * The listing update. Created by merging a {@link WsListingRemoveEvent} with a {@link WsListingAddEvent}
 */
public class WsListingUpdateEvent extends WsEvent<ListingUpdateEvent> {
    private final List<ListingView> added;
    private final List<ListingView> removed;

    /**
     * Creates a new websocket listing update event.
     *
     * @param item    item
     * @param world   world
     * @param added   added listings
     * @param removed removed listing
     */
    public WsListingUpdateEvent(Item item, World world, List<ListingView> added, List<ListingView> removed) {
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

    @Override
    public ListingUpdateEvent toEvent() {
        World world = world();
        List<Listing> added = this.added.stream().map(view -> view.toListing(world)).toList();
        List<Listing> removed = this.removed.stream().map(view -> view.toListing(world)).toList();
        return new ListingUpdateEvent(item(), world, added, removed);
    }
}
