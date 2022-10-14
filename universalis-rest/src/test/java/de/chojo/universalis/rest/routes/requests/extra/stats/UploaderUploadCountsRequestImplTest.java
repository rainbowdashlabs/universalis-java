/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class UploaderUploadCountsRequestImplTest {

    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        UploaderUploadCountResponse complete = rest.extra().stats().uploaderUploadCounts().complete();
        assertFalse(complete.uploader().isEmpty());
    }

}
