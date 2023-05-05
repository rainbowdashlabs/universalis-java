/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MostRecentlyUpdatedRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        var complete = rest.extra().stats().mostRecentlyUpdated().world(Worlds.europe().light().odin).complete();
        assertFalse(complete.items().isEmpty());
    }

}
