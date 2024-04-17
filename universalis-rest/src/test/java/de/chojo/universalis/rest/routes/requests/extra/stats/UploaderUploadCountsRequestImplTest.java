/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
