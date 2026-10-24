/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.AggregatedResponse;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("live")
class AggregatedRequestImplTest {

    @RetryingTest(3)
    public void dataCenter() {
        AggregatedResponse response = client().aggregated()
                .dataCenter(Worlds.europe().light())
                .itemsIds(5057, 5058)
                .complete();
        assertEquals(2, response.items().size());
    }
}
