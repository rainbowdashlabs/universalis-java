/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.HistoryRequest;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class HistoryRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultRest();

    @Test
    void limit() {
        HistoryResponse complete = defaultReq()
                .limit(3)
                .complete();
        Assertions.assertEquals(3, complete.sales().size());
    }

    @Test
    void historyTime() {
        HistoryResponse noGst = defaultReq().historyTime(Duration.ofDays(1)).complete();
        HistoryResponse gst = defaultReq().historyTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.sales().size(), gst.sales().size());
    }
    @Test
    void statsTime() {
        HistoryResponse noGst = defaultReq().statsTime(Duration.ofDays(7)).complete();
        HistoryResponse gst = defaultReq().statsTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.saleVelocity(), gst.saleVelocity());
    }

    private HistoryRequest defaultReq() {
        return rest.history()
                   .world(Worlds.europe().light().ODIN)
                   .itemsIds(36113);
    }
}
