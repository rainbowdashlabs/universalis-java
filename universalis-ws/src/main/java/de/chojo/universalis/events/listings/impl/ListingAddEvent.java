/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.listings.ListingEvent;

import java.util.List;

/**
 * Contains all reported listings.
 * <p>
 * These will be all reported listings even if these were reported before.
 * <p>
 * Eventually a {@link ListingRemoveEvent} event will be called beforehand removing all current listings.
 */
public class ListingAddEvent extends ListingEvent {
    /**
     * Create a new listing add event
     * @param item item
     * @param world world
     * @param listings listings
     */
    public ListingAddEvent(Item item, World world, List<Listing> listings) {
        super(item, world, listings);
    }
}
