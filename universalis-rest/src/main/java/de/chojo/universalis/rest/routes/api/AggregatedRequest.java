/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.AggregatedResponse;

/**
 * Request for aggregated market board data. Universalis computes it from cached values only, which makes it cheaper
 * than a {@link MarketBoardRequest} when single listings and sales are not needed.
 */
public interface AggregatedRequest extends Request<AggregatedResponse> {}
