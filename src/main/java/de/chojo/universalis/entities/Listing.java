/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.listing.views.MateriaView;
import de.chojo.universalis.worlds.World;

import java.time.Instant;
import java.util.List;

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
