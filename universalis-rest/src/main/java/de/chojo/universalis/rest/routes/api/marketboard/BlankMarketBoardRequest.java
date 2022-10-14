/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.rest.routes.api.base.DataCenterScope;
import de.chojo.universalis.rest.routes.api.base.RegionScope;
import de.chojo.universalis.rest.routes.api.base.WorldScope;

/**
 * Base for a {@link RegionMarketBoardRequest}
 */
public interface BlankMarketBoardRequest extends RegionScope<RegionMarketBoardRequest>, DataCenterScope<RegionMarketBoardRequest>, WorldScope<RegionMarketBoardRequest> {
}
