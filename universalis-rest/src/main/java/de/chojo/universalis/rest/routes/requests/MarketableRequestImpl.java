/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.MarketableResponse;
import de.chojo.universalis.rest.routes.api.MarketableRequest;

public class MarketableRequestImpl extends RequestBuilder<MarketableResponse> implements MarketableRequest {
    public MarketableRequestImpl(UniversalisRestImpl rest) {
        super(rest, MarketableResponse.class);
        path("marketable");
    }
}
