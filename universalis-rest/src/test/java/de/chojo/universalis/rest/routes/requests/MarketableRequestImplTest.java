/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.MarketableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;

class MarketableRequestImplTest {

    @Test
    public void test() {
        MarketableResponse complete = client().marketable().complete();
        Assertions.assertFalse(complete.items().isEmpty());
    }

}
