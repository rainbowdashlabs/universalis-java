/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;

class WorldUploadCountsRequestImplTest {


    @Test
    public void test() {
        WorldUploadCountResponse complete = client().extra().stats().worldUploadCounts().complete();
        Assertions.assertFalse(complete.worlds().isEmpty());
    }

}
