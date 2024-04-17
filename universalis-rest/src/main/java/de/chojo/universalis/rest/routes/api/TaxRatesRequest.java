/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
