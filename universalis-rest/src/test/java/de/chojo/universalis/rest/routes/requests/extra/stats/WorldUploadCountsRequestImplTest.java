/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WorldUploadCountsRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        WorldUploadCountResponse complete = rest.extra().stats().worldUploadCounts().complete();
        Assertions.assertFalse(complete.worlds().isEmpty());
    }

}
