/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.worlds.World;

import java.util.Collections;
import java.util.List;

/**
 * A listing update event. In order to receive this event the listing add and remove event needs to be subscribed on the world.
 */
public final class ListingUpdateEvent extends Event {
    private final List<Listing> added;
    private final List<Listing> removed;

    /**
     * Create a new listing update event
     * @param item item
     * @param world world
     * @param added added listings
     * @param removed removed listings
     */
    public ListingUpdateEvent(Item item, World world, List<Listing> added, List<Listing> removed) {
        super(world, item);
        this.added = added;
        this.removed = removed;
    }

    /**
     * The added listings
     *
     * @return unmodifiable list
     */
    public List<Listing> added() {
        return Collections.unmodifiableList(added);
    }

    /**
     * The removed listings
     *
     * @return unmodifiable list
     */
    public List<Listing> removed() {
        return Collections.unmodifiableList(removed);
    }
}
