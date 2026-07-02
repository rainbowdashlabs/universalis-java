/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.MarketableResponse;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.RetryingTest;

import static de.chojo.universalis.rest.ClientWrapper.client;

class MarketableRequestImplTest {

    @RetryingTest(3)
    public void test() {
        MarketableResponse complete = client().marketable().complete();
        Assertions.assertFalse(complete.items().isEmpty());
    }

}
