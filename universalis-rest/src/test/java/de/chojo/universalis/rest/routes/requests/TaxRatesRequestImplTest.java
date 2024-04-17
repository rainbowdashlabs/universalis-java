/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
