/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.deserializer.MillisDateTimeConverter;
import de.chojo.universalis.worlds.World;
import org.jetbrains.annotations.Nullable;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

/**
 * A sale.
 *
 * @param price     price per unit
 * @param timestamp when the item was sold
 * @param world     world of the sale; null in the world scope, where it is the requested world
 */
public record Purchase(
        @JsonProperty("price") int price,

        @JsonProperty("timestamp") @JsonDeserialize(converter = MillisDateTimeConverter.class)
        Instant timestamp,

        @JsonProperty("worldId") @Nullable World world) {}
