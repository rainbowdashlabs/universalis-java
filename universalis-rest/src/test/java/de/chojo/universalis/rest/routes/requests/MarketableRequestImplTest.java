/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.MarketableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MarketableRequestImplTest {
    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        MarketableResponse complete = rest.marketable().complete();
        Assertions.assertFalse(complete.items().isEmpty());
    }

}
