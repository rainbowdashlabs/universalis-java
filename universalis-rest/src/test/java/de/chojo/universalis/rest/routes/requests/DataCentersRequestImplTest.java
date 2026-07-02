/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.DataCentersResponse;
import org.junit.jupiter.api.Assertions;
import org.junitpioneer.jupiter.RetryingTest;

import static de.chojo.universalis.rest.ClientWrapper.client;

class DataCentersRequestImplTest {

    @RetryingTest(3)
    public void test() {
        DataCentersResponse complete = client().dataCenters().complete();
        Assertions.assertFalse(complete.dataCenters().isEmpty());
    }

}
