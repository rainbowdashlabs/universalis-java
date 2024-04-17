/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.DataCentersResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataCentersRequestImplTest {
    UniversalisRest rest = UniversalisRest.defaultApi();

    @Test
    public void test() {
        DataCentersResponse complete = rest.dataCenters().complete();
        Assertions.assertFalse(complete.dataCenters().isEmpty());
    }

}
