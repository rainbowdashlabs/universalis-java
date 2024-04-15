/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.worlds.World;

import java.time.LocalDateTime;

/**
 * A listing.
 *
 * @param lastReviewTime The time that this listing was posted.
 * @param world          the world
 * @param creator        the creator
 * @param meta           item meta
 * @param retainer       the retainer data
 * @param price          the price
 * @param listingId      A SHA256 hash of the ID of this listing. Due to some current client-side bugs, this will almost always be null.
 * @param onMannequin    Whether the item is being sold on a mannequin.
 * @param sellerId       A SHA256 hash of the seller's ID.
 * @param tax            The tax that will be added on top of the price.
 */
public record Listing(LocalDateTime lastReviewTime,
                      World world,
                      Creator creator,
                      ItemMeta meta,
                      String listingId,
                      boolean onMannequin,
                      Retainer retainer,
                      String sellerId,
                      Price price,
                      int tax
) {
}
