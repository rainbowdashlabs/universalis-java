/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.worlds.World;
import org.jetbrains.annotations.Nullable;

/**
 * A listing price.
 *
 * @param price price per unit
 * @param world world of the listing; null in the world scope, where it is the requested world, and for a median,
 *              which belongs to no single listing
 */
public record ListingPrice(
        @JsonProperty("price") int price,
        @JsonProperty("worldId") @Nullable World world) {}
