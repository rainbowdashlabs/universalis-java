/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.QualityIndicator;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Response of a {@link MarketBoardRequest}
 *
 * @param item                item
 * @param world               world
 * @param dataCenter          data center
 * @param region              region
 * @param lastUploadTime      last upload time
 * @param listings            listings
 * @param recentHistory       recent history
 * @param currentAveragePrice current average price
 * @param saleVelocity        sale velocity
 * @param averagePrice        average price
 * @param minPrice            min price
 * @param maxPrice            max price
 * @param stackSizeHistogram  stack size histogram
 * @param worldUploadTimes    world upload times
 */
public record MarketBoardResponse(Item item,
                                  @Nullable World world,
                                  @Nullable DataCenter dataCenter,
                                  @Nullable Region region,
                                  LocalDateTime lastUploadTime,
                                  List<Listing> listings,
                                  List<Sale> recentHistory,
                                  QualityIndicator<Float> currentAveragePrice,
                                  QualityIndicator<Float> saleVelocity,
                                  QualityIndicator<Float> averagePrice,
                                  QualityIndicator<Integer> minPrice,
                                  QualityIndicator<Integer> maxPrice,
                                  QualityIndicator<Map<Integer, Integer>> stackSizeHistogram,
                                  @Nullable Map<World, LocalDateTime> worldUploadTimes
) {
}
