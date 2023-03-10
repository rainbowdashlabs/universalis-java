/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.extra.stats.LeastRecentlyUpdatedResponse;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LeastRecentlyUpdatedRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        LeastRecentlyUpdatedResponse complete = rest.extra().stats().leastRecentlyUpdated()
                                                    .world(Worlds.europe().light().odin).complete();
        Assertions.assertFalse(complete.items().isEmpty());
    }

}
