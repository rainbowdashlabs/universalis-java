/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestUriTest {
    private static final UniversalisRest rest = UniversalisRest.defaultApi();

    @AfterAll
    static void close() {
        rest.close();
    }

    @Test
    void marketBoardFields() {
        var request = rest.marketBoard()
                .world(Worlds.europe().light().odin)
                .itemsIds(5057)
                .fields("listings.pricePerUnit", "minPriceHQ");
        assertEquals(
                "MarketBoardRequestImpl: https://universalis.app/api/v2/66/5057?fields=listings.pricePerUnit%2CminPriceHQ",
                request.toString());
    }
}
