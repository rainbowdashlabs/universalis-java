/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.worlds.World;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * A sale object
 *
 * @param hq          hq
 * @param price       price
 * @param timestamp   timestamp
 * @param onMannequin Whether this was purchased from a mannequin.
 * @param world       world
 * @param buyerName   The buyer's character name. This may be null.
 */
public record Sale(boolean hq,
                   Price price,
                   LocalDateTime timestamp,
                   boolean onMannequin,
                   World world,
                   @Nullable String buyerName) {
}
