/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.listings.ListingEvent;

import java.util.List;

/**
 * Called when listings get removed. This usually happens when a listing gets updated and before a {@link ListingAddEvent} event is called.
 * <p>
 * This event is ideally identical in content to the previous {@link ListingAddEvent} event.
 */
public class ListingRemoveEvent extends ListingEvent {
    /**
     * Create a new listing remove event
     * @param item item
     * @param world world
     * @param listings listings
     */
    public ListingRemoveEvent(Item item, World world, List<Listing> listings) {
        super(item, world, listings);
    }
}
