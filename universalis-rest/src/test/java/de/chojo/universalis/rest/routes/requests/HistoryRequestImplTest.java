/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.routes.api.HistoryRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

class HistoryRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    void limit() {
        HistoryResponse complete = defaultReq()
                .limit(3)
                .complete();
        Assertions.assertEquals(3, complete.sales().size());
    }

    @Test
    void historyTime() {
        HistoryResponse noGst = defaultReq().historyTime(Duration.ofDays(7)).complete();
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
                   .world(Worlds.europe().light().odin)
                   .itemsIds(33927);
    }
}
