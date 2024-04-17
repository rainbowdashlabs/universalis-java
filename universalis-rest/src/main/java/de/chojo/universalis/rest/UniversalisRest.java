/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import de.chojo.universalis.rest.routes.api.DataCentersRequest;
import de.chojo.universalis.rest.routes.api.MarketableRequest;
import de.chojo.universalis.rest.routes.api.WorldsRequest;
import de.chojo.universalis.rest.routes.api.history.BlankHistoryRequest;
import de.chojo.universalis.rest.routes.api.marketboard.BlankMarketBoardRequest;
import de.chojo.universalis.rest.routes.api.taxrates.BlankTaxRatesRequest;
import de.chojo.universalis.rest.routes.requests.extra.Extra;

import javax.annotation.CheckReturnValue;

/**
 * Class to access the universalis api.
 */
public interface UniversalisRest {
    /**
     * Get a api with default settings.
     *
     * @return api instance
     */
    @CheckReturnValue
    static UniversalisRest defaultApi() {
        return new UniversalisRestBuilder().build();
    }

    /**
     * Get a builder to configure the api.
     *
     * @return builder
     */
    @CheckReturnValue
    static UniversalisRestBuilder builder() {
        return new UniversalisRestBuilder();
    }

    /**
     * Retrieves the data currently shown on the market board for the requested item and world or data center.
     *
     * @return request builder
     */
    @CheckReturnValue
    BlankMarketBoardRequest marketBoard();

    /**
     * Returns the IDs and names of all worlds supported by the API.
     *
     * @return request builder
     */
    @CheckReturnValue
    WorldsRequest worlds();

    /**
     * Returns all data centers supported by the API.
     *
     * @return request builder
     */
    @CheckReturnValue
    DataCentersRequest dataCenters();

    /**
     * Retrieves the history data for the requested item and world or data center.
     *
     * @return request builder
     */
    @org.jetbrains.annotations.CheckReturnValue
    BlankHistoryRequest history();

    /**
     * Retrieves the current tax rate data for the specified world. This data is provided by the Retainer Vocate in each major city.
     *
     * @return request builder
     */
    @CheckReturnValue
    BlankTaxRatesRequest taxRates();

    /**
     * Returns the set of marketable item IDs.
     *
     * @return request builder
     */
    @CheckReturnValue
    MarketableRequest marketable();

    /**
     * Allows requests on route of the extra route
     *
     * @return extra route
     */
    @CheckReturnValue
    Extra extra();
}
