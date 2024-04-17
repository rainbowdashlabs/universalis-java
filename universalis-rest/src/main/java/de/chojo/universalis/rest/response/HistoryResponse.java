/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.MinimizedSale;
import de.chojo.universalis.entities.QualityIndicator;
import de.chojo.universalis.rest.routes.api.HistoryRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Response of a {@link HistoryRequest}
 *
 * @param item               item
 * @param world              world
 * @param dataCenter         data center
 * @param region             region
 * @param lastUploadTime     last upload time
 * @param sales              sales
 * @param saleVelocity       sale velocity
 * @param stackSizeHistogram stack size histogram
 */
public record HistoryResponse(Item item,
                              @Nullable World world,
                              @Nullable DataCenter dataCenter,
                              @Nullable Region region,
                              LocalDateTime lastUploadTime,
                              List<MinimizedSale> sales,
                              QualityIndicator<Float> saleVelocity,
                              QualityIndicator<Map<Integer, Integer>> stackSizeHistogram) {
}
