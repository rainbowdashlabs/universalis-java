/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.events.Event;

import java.util.List;

public final class ListingUpdateEvent extends Event {
    private final List<Listing> added;
    private final List<Listing> removed;

    public ListingUpdateEvent(Item item, World world, List<Listing> added, List<Listing> removed) {
        super(world, item);
        this.added = added;
        this.removed = removed;
    }

    public List<Listing> added() {
        return added;
    }

    public List<Listing> removed() {
        return removed;
    }
}
