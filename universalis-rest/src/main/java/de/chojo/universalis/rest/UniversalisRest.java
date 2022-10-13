/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import de.chojo.universalis.rest.routes.api.taxrates.BlankTaxRatesRequest;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;
import de.chojo.universalis.rest.routes.api.WorldsRequest;
import de.chojo.universalis.rest.routes.api.history.BlankHistoryRequest;
import de.chojo.universalis.rest.routes.api.marketboard.BlankMarketBoardRequest;

public interface UniversalisRest {
    BlankMarketBoardRequest marketBoard();

    static UniversalisRest defaultRest() {
        return new UniversalisRestImpl();
    }

    WorldsRequest worlds();

    DataCentersRequest dataCenters();

    BlankHistoryRequest history();

    BlankTaxRatesRequest taxRates();
}
