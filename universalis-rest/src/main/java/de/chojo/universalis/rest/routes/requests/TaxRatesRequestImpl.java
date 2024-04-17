/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.rest.routes.api.TaxRatesRequest;
import de.chojo.universalis.worlds.World;

import org.jetbrains.annotations.CheckReturnValue;

/**
 * Implementation for a {@link TaxRatesRequest}
 */
public class TaxRatesRequestImpl extends RequestBuilder<TaxRatesResponse> implements TaxRatesRequest {
    /**
     * Create a new tax rates request
     *
     * @param rest rest client
     */
    public TaxRatesRequestImpl(UniversalisRestImpl rest) {
        super(rest, TaxRatesResponse.class);
        path("tax-rates");
    }

    @Override
    @CheckReturnValue
    public TaxRatesRequest world(World world) {
        parameter("world", world.name());
        return this;
    }
}
