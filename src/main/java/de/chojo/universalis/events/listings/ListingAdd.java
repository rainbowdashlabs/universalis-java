/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.worlds.World;

import java.util.List;

public class ListingAdd extends BaseListing {
    public ListingAdd(Item item, World world, List<Listing> listings) {
        super(item, world, listings);
    }
}
