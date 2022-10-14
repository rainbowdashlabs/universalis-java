/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.MinimizedSale;
import de.chojo.universalis.entities.QualityIndicator;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public record HistoryResponse(Item item,
                              @Nullable World world,
                              @Nullable DataCenter datacenter,
                              @Nullable Region region,
                              Instant lastUploadTime,
                              List<MinimizedSale> sales,
                              QualityIndicator<Float> saleVelocity,
                              QualityIndicator<Map<Integer, Integer>> stackSizeHistogram) {
}
