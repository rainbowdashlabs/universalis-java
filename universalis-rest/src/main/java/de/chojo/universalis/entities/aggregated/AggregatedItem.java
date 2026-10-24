/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;

import java.util.List;

/**
 * Aggregated market board data of one item.
 *
 * @param item             item
 * @param normalQuality    values of normal quality listings and sales
 * @param highQuality      values of high quality listings and sales
 * @param worldUploadTimes last upload per world
 */
public record AggregatedItem(
        @JsonProperty("itemId") Item item,
        @JsonProperty("nq") AggregatedValues normalQuality,
        @JsonProperty("hq") AggregatedValues highQuality,
        @JsonProperty("worldUploadTimes") List<WorldUploadTime> worldUploadTimes) {}
