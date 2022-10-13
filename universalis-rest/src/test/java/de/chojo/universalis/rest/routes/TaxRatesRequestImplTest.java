/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxRatesRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultRest();

    @Test
    public void test() {
        TaxRatesResponse complete = rest.taxRates().world(Worlds.europe().light().ODIN).complete();
        assertEquals(7, complete.rates().size());
    }
}
