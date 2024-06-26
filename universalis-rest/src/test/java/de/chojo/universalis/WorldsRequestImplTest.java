/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import de.chojo.universalis.rest.response.WorldsResponse;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertFalse;

class WorldsRequestImplTest {

    @Test
    public void test() {
        WorldsResponse complete = client().worlds().complete();
        assertFalse(complete.worlds().isEmpty());
    }

}
