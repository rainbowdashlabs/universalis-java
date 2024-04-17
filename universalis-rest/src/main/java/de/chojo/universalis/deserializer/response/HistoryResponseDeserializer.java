/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.MinimizedSale;
import de.chojo.universalis.entities.QualityIndicator;
import de.chojo.universalis.entities.views.HistoryView;
import de.chojo.universalis.entities.views.MinimizedSaleView;
import de.chojo.universalis.rest.response.HistoryResponse;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Deserializer for {@link HistoryResponse}
 */
public class HistoryResponseDeserializer extends JsonDeserializer<HistoryResponse> {
    @Override
    public HistoryResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        var view = ctxt.readValue(p, HistoryView.class);
        var item = view.item();
        var world = view.world();
        var datacenter = view.datacenter();
        var region = view.region();
        var lastUploadTime = view.lastUploadTime();
        List<MinimizedSale> sales = Collections.emptyList();
        if (view.entries() != null) {
            sales = view.entries().stream().map(MinimizedSaleView::toSale).toList();
        }
        var saleVelocity = QualityIndicator.of(view.regularSaleVelocity(), view.nqSaleVelocity(), view.hqSaleVelocity());
        var histogramG = view.stackSizeHistogram().entrySet().stream()
                                       .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var histogramNQ = view.stackSizeHistogram().entrySet().stream()
                                        .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var histogramHQ = view.stackSizeHistogram().entrySet().stream()
                                        .collect(Collectors.toMap(e -> Integer.valueOf(e.getKey()), Map.Entry::getValue));
        var histogram = QualityIndicator.of(histogramG, histogramNQ, histogramHQ);
        return new HistoryResponse(item, world, datacenter, region, lastUploadTime, sales, saleVelocity,
                histogram);
    }
}
