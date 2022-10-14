/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.rest.routes.api.TaxRatesRequest;

public class TaxRatesRequestImpl extends RequestBuilder<TaxRatesResponse> implements TaxRatesRequest {
    public TaxRatesRequestImpl(UniversalisRestImpl rest) {
        super(rest, TaxRatesResponse.class);
        path("tax-rates");
    }

    @Override
    public TaxRatesRequest world(World world) {
        parameter("world", world.name());
        return this;
    }
}
