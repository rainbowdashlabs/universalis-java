/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.QualityIndicator;
import de.chojo.universalis.entities.views.CurrentlyShownView;
import de.chojo.universalis.entities.views.ListingView;
import de.chojo.universalis.entities.views.SaleView;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Deserializer for {@link MarketBoardResponse}
 */
public class MarketBoardResponseDeserializer extends JsonDeserializer<MarketBoardResponse> {
    @Override
    public MarketBoardResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var view = ctxt.readValue(p, CurrentlyShownView.class);
        var item = view.item();
        var world = view.world();
        var datacenter = view.datacenter();
        var region = view.region();
        var lastUploadTime = view.lastUploadTime();
        var listings = view.listingViews().stream().map(ListingView::toListing).toList();
        var sales = view.recentHistory().stream().map(SaleView::toSale).toList();
        var currentAveragePrice = QualityIndicator.of(view.currentAveragePrice(), view.currentAveragePriceNQ(), view.currentAveragePriceHQ());
        var saleVelocity = QualityIndicator.of(view.regularSaleVelocity(), view.nqSaleVelocity(), view.hqSaleVelocity());
        var averagePrice = QualityIndicator.of(view.averagePrice(), view.averagePriceNQ(), view.averagePriceHQ());
        var minPrice = QualityIndicator.of(view.minPrice(), view.minPriceNQ(), view.minPriceHQ());
        var maxPrice = QualityIndicator.of(view.maxPrice(), view.maxPriceNQ(), view.maxPriceHQ());
        var histogramG = view.stackSizeHistogram().entrySet().stream()
                                       .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var histogramNQ = view.stackSizeHistogramNQ().entrySet().stream()
                                        .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var histogramHQ = view.stackSizeHistogramHQ().entrySet().stream()
                                        .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var stackSizeHistogram = QualityIndicator.of(histogramG, histogramNQ, histogramHQ);
        Map<World, LocalDateTime> worldUploadTimes = Collections.emptyMap();
        if (view.worldUploadTimes() != null) {
            worldUploadTimes = view.worldUploadTimes()
                                   .entrySet()
                                   .stream()
                                   .collect(Collectors.toMap(
                                           e -> Worlds.worldById(Integer.parseInt(e.getKey())),
                                           e -> Instant.ofEpochMilli(e.getValue()).atOffset(ZoneOffset.UTC)
                                                       .toLocalDateTime())
                                   );
        }
        return new MarketBoardResponse(item, world, datacenter, region, lastUploadTime, listings, sales, currentAveragePrice, saleVelocity,
                averagePrice, minPrice, maxPrice, stackSizeHistogram, worldUploadTimes);
    }
}
