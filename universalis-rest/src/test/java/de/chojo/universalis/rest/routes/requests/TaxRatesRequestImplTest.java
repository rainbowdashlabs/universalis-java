/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxRatesRequestImplTest {


    @Test
    public void test() {
        TaxRatesResponse complete = client().taxRates().world(Worlds.europe().light().odin).complete();
        assertEquals(7, complete.rates().size());
    }
}
