/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
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
