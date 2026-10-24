/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.UploadHistoryResponse;
import org.junit.jupiter.api.Tag;
import org.junitpioneer.jupiter.RetryingTest;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Tag("live")
class UploadHistoryRequestImplTest {

    @RetryingTest(3)
    public void test() {
        UploadHistoryResponse complete =
                client().extra().stats().uploadHistory().complete();
        assertFalse(complete.uploadCountByDay().isEmpty());
    }
}
