/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.listings.impl;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.shared.Item;
import de.chojo.universalis.entities.shared.World;

import java.util.List;

public record ListingUpdate(Item item, World world, List<Listing> added, List<Listing> removed) {
}
