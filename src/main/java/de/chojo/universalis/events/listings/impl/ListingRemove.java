/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.shared.Item;
import de.chojo.universalis.entities.shared.World;
import de.chojo.universalis.events.listings.BaseListing;

import java.util.List;

/**
 * Called when listings get removed. This usually happens when a listing gets updated and before a {@link ListingAdd} event is called.
 * <p>
 * This event is ideally identical in content to the previous {@link ListingAdd} event.
 */
public class ListingRemove extends BaseListing {
    public ListingRemove(Item item, World world, List<Listing> listings) {
        super(item, world, listings);
    }
}
