/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.MarketableResponse;
import de.chojo.universalis.rest.routes.requests.MarketBoardRequestImpl;

/**
 * Base implementation for a {@link MarketBoardRequestImpl}
 */
public interface MarketableRequest extends Request<MarketableResponse> {
}
