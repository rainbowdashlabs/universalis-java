/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.response.DataCentersResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static de.chojo.universalis.rest.ClientWrapper.client;

class DataCentersRequestImplTest {

    @Test
    public void test() {
        DataCentersResponse complete = client().dataCenters().complete();
        Assertions.assertFalse(complete.dataCenters().isEmpty());
    }

}
