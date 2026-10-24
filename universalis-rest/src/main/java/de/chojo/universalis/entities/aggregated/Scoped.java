/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

/**
 * A value for the requested world, its data center and its region. Universalis only fills the scopes that the request
 * covers: a data center request has no world value, a region request only a region value.
 *
 * @param world      value on the requested world
 * @param dataCenter value on the data center
 * @param region     value in the region
 * @param <T>        type of the value
 */
public record Scoped<T>(
        @JsonProperty("world") @Nullable T world,
        @JsonProperty("dc") @Nullable T dataCenter,
        @JsonProperty("region") @Nullable T region) {}
