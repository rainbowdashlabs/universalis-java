/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.rest.routes.api.taxrates.BlankTaxRatesRequest;
import de.chojo.universalis.rest.routes.requests.TaxRatesRequestImpl;

/**
 * Base implementation for a {@link TaxRatesRequestImpl}
 */
public interface TaxRatesRequest extends Request<TaxRatesResponse>, BlankTaxRatesRequest {
}
