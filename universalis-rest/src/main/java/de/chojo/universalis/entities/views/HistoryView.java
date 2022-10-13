/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.worlds.Datacenter;
import de.chojo.universalis.worlds.Region;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public record HistoryView(@JsonProperty("itemID") Item item,
                          @JsonProperty("worldID")@Nullable World world,
                          @JsonProperty("lastUploadTime") Instant lastUploadTime,
                          @JsonProperty("entries")@Nullable List<MinimizedSaleView> entries,
                          @JsonProperty("dcName")@Nullable Datacenter datacenter,
                          @JsonProperty("region")@Nullable Region region,
                          @JsonProperty("stackSizeHistogram")@Nullable Map<String, Integer> stackSizeHistogram,
                          @JsonProperty("stackSizeHistogramNQ")@Nullable Map<String, Integer> stackSizeHistogramNQ,
                          @JsonProperty("stackSizeHistogramHQ")@Nullable Map<String, Integer> stackSizeHistogramHQ,
                          @JsonProperty("regularSaleVelocity") float regularSaleVelocity,
                          @JsonProperty("nqSaleVelocity") float nqSaleVelocity,
                          @JsonProperty("hqSaleVelocity") float hqSaleVelocity
                          ) {
}
