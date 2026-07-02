/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.rest.requests.Mapper;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Offline deserializer tests using inline JSON fixtures. These regressions catch
 * bugs that only surface with distinct NQ/HQ/general histogram data — before the
 * fix, {@code HistoryResponse} returned identical histograms and
 * {@code CurrentlyShownView} silently emptied out NQ/general whenever HQ was null.
 */
class DeserializerFixtureTest {
    private final ObjectMapper mapper = Mapper.create(NameSupplier.EMPTY);

    @Test
    void historyResponseKeepsDistinctHistograms() {
        String json = """
                {
                  "itemID": 5057,
                  "worldID": 66,
                  "lastUploadTime": 1700000000000,
                  "entries": [],
                  "dcName": "Light",
                  "regularSaleVelocity": 1.0,
                  "nqSaleVelocity": 2.0,
                  "hqSaleVelocity": 3.0,
                  "stackSizeHistogram":    {"1": 10, "5": 20},
                  "stackSizeHistogramNQ":  {"1": 100},
                  "stackSizeHistogramHQ":  {"1": 999, "3": 42}
                }
                """;

        HistoryResponse response = mapper.readValue(json, HistoryResponse.class);

        Map<Integer, Integer> general = response.stackSizeHistogram().general();
        Map<Integer, Integer> nq = response.stackSizeHistogram().normalQuality();
        Map<Integer, Integer> hq = response.stackSizeHistogram().highQuality();

        assertEquals(Map.of(1, 10, 5, 20), general, "general histogram must come from stackSizeHistogram");
        assertEquals(Map.of(1, 100), nq, "NQ histogram must come from stackSizeHistogramNQ");
        assertEquals(Map.of(1, 999, 3, 42), hq, "HQ histogram must come from stackSizeHistogramHQ");
    }

    @Test
    void marketBoardResponseGeneralAndNqSurviveMissingHq() {
        String json = """
                {
                  "itemID": 5057,
                  "worldID": 66,
                  "lastUploadTime": 1700000000000,
                  "listings": [],
                  "recentHistory": [],
                  "dcName": "Light",
                  "regionName": "Europe",
                  "currentAveragePrice": 0.0,
                  "currentAveragePriceNQ": 0.0,
                  "currentAveragePriceHQ": 0.0,
                  "regularSaleVelocity": 0.0,
                  "nqSaleVelocity": 0.0,
                  "hqSaleVelocity": 0.0,
                  "averagePrice": 0.0,
                  "averagePriceNQ": 0.0,
                  "averagePriceHQ": 0.0,
                  "minPrice": 0, "minPriceNQ": 0, "minPriceHQ": 0,
                  "maxPrice": 0, "maxPriceNQ": 0, "maxPriceHQ": 0,
                  "stackSizeHistogram":   {"1": 7},
                  "stackSizeHistogramNQ": {"1": 3}
                }
                """;

        MarketBoardResponse response = mapper.readValue(json, MarketBoardResponse.class);

        assertEquals(7, response.stackSizeHistogram().general().get(1),
                "general histogram must survive missing HQ");
        assertEquals(3, response.stackSizeHistogram().normalQuality().get(1),
                "NQ histogram must survive missing HQ");
        assertNotNull(response.stackSizeHistogram().highQuality(),
                "HQ histogram must be non-null (empty map) when missing from payload");
        assertTrue(response.stackSizeHistogram().highQuality().isEmpty(),
                "missing HQ histogram should deserialize as empty");
    }
}
