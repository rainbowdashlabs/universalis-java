/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UploaderUploadCountsRequestImplTest {


    @Test
    public void test() {
        UploaderUploadCountResponse complete = client().extra().stats().uploaderUploadCounts().complete();
        assertFalse(complete.uploader().isEmpty());
    }

}
