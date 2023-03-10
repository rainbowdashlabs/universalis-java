/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.rest.routes.api.base.ItemScope;

/**
 * Base for a {@link MarketBoardRequest}
 */
public interface RegionMarketBoardRequest extends BlankMarketBoardRequest, ItemScope<MarketBoardRequest> {
}
