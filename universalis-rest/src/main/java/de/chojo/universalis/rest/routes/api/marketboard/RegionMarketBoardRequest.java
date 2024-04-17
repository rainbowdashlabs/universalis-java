/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.rest.routes.api.base.ItemScope;

/**
 * Base for a {@link MarketBoardRequest}
 */
public interface RegionMarketBoardRequest extends BlankMarketBoardRequest, ItemScope<MarketBoardRequest> {
}
