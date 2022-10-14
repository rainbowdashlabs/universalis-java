/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * Represents a listing event.
 */
public abstract class ListingEvent extends Event {
    private final List<Listing> listings;

    /**
     * Creates a new listing event
     *
     * @param item     item
     * @param world    world
     * @param listings listings
     */
    public ListingEvent(Item item, World world, List<Listing> listings) {
        super(world, item);
        this.listings = listings;
    }

    /**
     * Listings of this event
     *
     * @return unmodifiable list
     */
    public List<Listing> listings() {
        return listings;
    }
}
