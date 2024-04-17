/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.MillisDateTimeConverter;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import org.jetbrains.annotations.Nullable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <a href="https://docs.universalis.app/#schema-historyview">See on universalis</a>
 *
 * @param item                 item
 * @param world                world
 * @param lastUploadTime       The last upload time for this endpoint
 * @param entries              The historical sales.
 * @param datacenter           datacenter
 * @param region               region
 * @param stackSizeHistogram   A map of quantities to sale counts, representing the number of sales of each quantity.
 * @param stackSizeHistogramNQ A map of quantities to NQ sale counts, representing the number of sales of each quantity.
 * @param stackSizeHistogramHQ A map of quantities to HQ sale counts, representing the number of sales of each quantity.
 * @param regularSaleVelocity  The average number of sales per day, over the past seven days (or the entirety of the shown sales, whichever comes first).
 * @param nqSaleVelocity       The average number of NQ sales per day, over the past seven days (or the entirety of the shown sales, whichever comes first).
 * @param hqSaleVelocity       The average number of HQ sales per day, over the past seven days (or the entirety of the shown sales, whichever comes first).
 */
public record HistoryView(@JsonProperty("itemID") Item item,
                          @JsonProperty("worldID") @Nullable World world,
                          @JsonProperty("lastUploadTime")  @JsonDeserialize(converter = MillisDateTimeConverter.class) LocalDateTime lastUploadTime,
                          @JsonProperty("entries") @Nullable List<MinimizedSaleView> entries,
                          @JsonProperty("dcName") @Nullable DataCenter datacenter,
                          @JsonProperty("region") @Nullable Region region,
                          @JsonProperty("stackSizeHistogram") @Nullable Map<String, Integer> stackSizeHistogram,
                          @JsonProperty("stackSizeHistogramNQ") @Nullable Map<String, Integer> stackSizeHistogramNQ,
                          @JsonProperty("stackSizeHistogramHQ") @Nullable Map<String, Integer> stackSizeHistogramHQ,
                          @JsonProperty("regularSaleVelocity") float regularSaleVelocity,
                          @JsonProperty("nqSaleVelocity") float nqSaleVelocity,
                          @JsonProperty("hqSaleVelocity") float hqSaleVelocity
) {
}
