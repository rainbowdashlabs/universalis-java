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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

class HistoryRequestImplTest {

    static UniversalisRest rest = UniversalisRest.defaultApi();

    @ParameterizedTest
    @MethodSource("inputs")
    void limit(HistoryRequest request) {
        HistoryResponse complete = request
                .limit(3)
                .complete();
        Assertions.assertEquals(3, complete.sales().size());
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void historyTime(HistoryRequest request) {
        HistoryResponse noGst = request.historyTime(Duration.ofDays(7)).complete();
        HistoryResponse gst = request.historyTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.sales().size(), gst.sales().size());
    }

    @ParameterizedTest
    @MethodSource("inputs")
    void statsTime(HistoryRequest request) {
        HistoryResponse noGst = request.statsTime(Duration.ofDays(7)).complete();
        HistoryResponse gst = request.statsTime(Duration.ofHours(1)).complete();
        Assertions.assertNotEquals(noGst.saleVelocity(), gst.saleVelocity());
    }


    public static Stream<HistoryRequest> inputs() {
        return Stream.of(worldReq(), dcReq(), regionReq());
    }

    private static HistoryRequest worldReq() {
        return rest.history()
                .world(Worlds.europe().light().odin)
                .itemsIds(33927);
    }

    private static HistoryRequest dcReq() {
        return rest.history()
                .dataCenter(Worlds.europe().light())
                .itemsIds(33927);
    }

    private static HistoryRequest regionReq() {
        return rest.history()
                .region(Worlds.europe())
                .itemsIds(33927);
    }
}
