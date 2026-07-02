/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.RetryingTest;

import static de.chojo.universalis.rest.ClientWrapper.client;

class WorldUploadCountsRequestImplTest {

    @RetryingTest(3)
    public void test() {
        WorldUploadCountResponse complete = client().extra().stats().worldUploadCounts().complete();
        Assertions.assertFalse(complete.worlds().isEmpty());
    }

}
