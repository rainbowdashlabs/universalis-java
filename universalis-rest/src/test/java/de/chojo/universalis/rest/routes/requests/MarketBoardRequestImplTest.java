/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class MarketBoardRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    void historyLimit() {
        MarketBoardResponse complete = defaultReq()
                .historyLimit(3)
                .complete();
        Assertions.assertEquals(3, complete.recentHistory().size());
    }

    @Test
    void listingLimit() {
        MarketBoardResponse complete = defaultReq()
                .listingsLimit(3)
                .complete();
        Assertions.assertTrue(complete.listings().size() > 0);
        Assertions.assertTrue(complete.listings().size() <= 3);
    }

    @Test
    void highQuality() {
        var complete = defaultReq().highQuality().complete();
        for (Listing listing : complete.listings()) {
            Assertions.assertTrue(listing.meta().hq());
        }

    }

    @Test
    void normalQuality() {
        var complete = defaultReq().normalQuality().complete();
        for (Listing listing : complete.listings()) {
            Assertions.assertFalse(listing.meta().hq());
        }

    }

    @Test
    void historyTime() {
        MarketBoardResponse noGst = defaultReq().historyTime(Duration.ofDays(7)).complete();
        MarketBoardResponse gst = defaultReq().historyTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.recentHistory().size(), gst.recentHistory().size());
    }

    @Test
    void statsTime() {
        MarketBoardResponse noGst = defaultReq().statsTime(Duration.ofDays(7)).complete();
        MarketBoardResponse gst = defaultReq().statsTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.saleVelocity(), gst.saleVelocity());
    }

    private MarketBoardRequest defaultReq() {
        return rest.marketBoard()
                   .world(Worlds.europe().light().odin)
                   .itemsIds(33927);
    }
}
