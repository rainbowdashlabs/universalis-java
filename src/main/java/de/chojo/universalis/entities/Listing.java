/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

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
