/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.UploadHistoryResponse;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UploadHistoryRequestImplTest {


    @Test
    public void test() {
        UploadHistoryResponse complete = client().extra().stats().uploadHistory().complete();
        assertFalse(complete.uploadCountByDay().isEmpty());
    }
}
