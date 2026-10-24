/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.deserializer.MillisDateTimeConverter;
import de.chojo.universalis.worlds.World;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;

/**
 * When data of a world was last uploaded.
 *
 * @param world     world
 * @param timestamp time of the last upload
 */
public record WorldUploadTime(
        @JsonProperty("worldId") World world,

        @JsonProperty("timestamp") @JsonDeserialize(converter = MillisDateTimeConverter.class)
        Instant timestamp) {}
