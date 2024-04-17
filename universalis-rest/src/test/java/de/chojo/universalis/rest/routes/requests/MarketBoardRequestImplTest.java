/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static de.chojo.universalis.rest.ClientWrapper.client;

class MarketBoardRequestImplTest {

    @ParameterizedTest
    @MethodSource("inputs")
    void historyLimit(MarketBoardRequest request) {
        MarketBoardResponse complete = request
                .historyLimit(3)
                .complete();
        Assertions.assertEquals(3, complete.recentHistory().size());
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void listingLimit(MarketBoardRequest request) {
        MarketBoardResponse complete = request
                .listingsLimit(3)
                .complete();
        Assertions.assertFalse(complete.listings().isEmpty());
        Assertions.assertTrue(complete.listings().size() <= 3);
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void highQuality(MarketBoardRequest request) {
        var complete = request.highQuality().complete();
        for (Listing listing : complete.listings()) {
            Assertions.assertTrue(listing.meta().hq());
        }

    }

    @ParameterizedTest
    @MethodSource("inputs")
    void normalQuality(MarketBoardRequest request) {
        var complete = request.normalQuality().complete();
        for (Listing listing : complete.listings()) {
            Assertions.assertFalse(listing.meta().hq());
        }

    }

    @ParameterizedTest
    @MethodSource("inputs")
    void historyTime(MarketBoardRequest request) {
        MarketBoardResponse days = request.historyTime(Duration.ofDays(7)).complete();
        MarketBoardResponse hours = request.historyTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(days.recentHistory().size(), hours.recentHistory().size());
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void statsTime(MarketBoardRequest request) {
        MarketBoardResponse days = request.statsTime(Duration.ofDays(7)).complete();
        MarketBoardResponse hours = request.statsTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(days.saleVelocity(), hours.saleVelocity());
    }

    public static Stream<MarketBoardRequest> inputs() {
        return Stream.of(worldReq(), dcReq(), regionReq());
    }

    private static MarketBoardRequest worldReq() {
        return client().marketBoard()
                .world(Worlds.europe().light().odin)
                .itemsIds(33927);
    }

    private static MarketBoardRequest dcReq() {
        return client().marketBoard()
                .dataCenter(Worlds.europe().light())
                .itemsIds(33927);
    }

    private static MarketBoardRequest regionReq() {
        return client().marketBoard()
                .region(Worlds.europe())
                .itemsIds(33927);
    }
}
