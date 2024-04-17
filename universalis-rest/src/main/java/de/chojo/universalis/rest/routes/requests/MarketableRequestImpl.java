/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.MarketableResponse;
import de.chojo.universalis.rest.routes.api.MarketableRequest;

/**
 * Implementation for a {@link MarketableRequest}
 */
public class MarketableRequestImpl extends RequestBuilder<MarketableResponse> implements MarketableRequest {
    /**
     * Create a new marketable request
     *
     * @param rest rest client
     */
    public MarketableRequestImpl(UniversalisRestImpl rest) {
        super(rest, MarketableResponse.class);
        path("marketable");
    }
}
