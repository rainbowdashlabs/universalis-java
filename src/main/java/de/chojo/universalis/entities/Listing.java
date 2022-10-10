/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.entities.shared.Creator;
import de.chojo.universalis.entities.shared.ItemMeta;
import de.chojo.universalis.entities.shared.Price;
import de.chojo.universalis.entities.shared.Retainer;
import de.chojo.universalis.entities.shared.World;

import java.time.Instant;

public record Listing(Instant lastReviewTime,
                      World world,
                      Creator creator,
                      ItemMeta meta,
                      String listingId,
                      boolean onManequin,
                      Retainer retainer,
                      String sellerId,
                      Price price
) {
}
