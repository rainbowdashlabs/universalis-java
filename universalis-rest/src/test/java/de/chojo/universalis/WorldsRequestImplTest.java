/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.WorldsResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class WorldsRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        WorldsResponse complete = rest.worlds().complete();
        assertFalse(complete.worlds().isEmpty());
    }

}
