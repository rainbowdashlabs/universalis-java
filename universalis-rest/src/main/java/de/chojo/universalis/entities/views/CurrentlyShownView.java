/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public record CurrentlyShownView(@JsonProperty("itemID") Item item,
                                 @JsonProperty("worldID") World world,
                                 @JsonProperty("worldUploadTimes") @Nullable Map<String, Integer> worldUploadTimes,
                                 @JsonProperty("lastUploadTime") Instant lastUploadTime,
                                 @JsonProperty("listings") List<ListingView> listingViews,
                                 @JsonProperty("recentHistory") List<SaleView> recentHistory,
                                 @JsonProperty("dcName") DataCenter datacenter,
                                 @JsonProperty("regionName") Region region,
                                 @JsonProperty("currentAveragePrice") float currentAveragePrice,
                                 @JsonProperty("currentAveragePriceNQ") float currentAveragePriceNQ,
                                 @JsonProperty("currentAveragePriceHQ") float currentAveragePriceHQ,
                                 @JsonProperty("regularSaleVelocity") float regularSaleVelocity,
                                 @JsonProperty("nqSaleVelocity") float nqSaleVelocity,
                                 @JsonProperty("hqSaleVelocity") float hqSaleVelocity,
                                 @JsonProperty("averagePrice") float averagePrice,
                                 @JsonProperty("averagePriceNQ") float averagePriceNQ,
                                 @JsonProperty("averagePriceHQ") float averagePriceHQ,
                                 @JsonProperty("minPrice") int minPrice,
                                 @JsonProperty("minPriceNQ") int minPriceNQ,
                                 @JsonProperty("minPriceHQ") int minPriceHQ,
                                 @JsonProperty("maxPrice") int maxPrice,
                                 @JsonProperty("maxPriceNQ") int maxPriceNQ,
                                 @JsonProperty("maxPriceHQ") int maxPriceHQ,
                                 @JsonProperty("stackSizeHistogram") Map<String, Integer> stackSizeHistogram,
                                 @JsonProperty("stackSizeHistogramNQ") Map<String, Integer> stackSizeHistogramNQ,
                                 @JsonProperty("stackSizeHistogramHQ") Map<String, Integer> stackSizeHistogramHQ
) {
}
