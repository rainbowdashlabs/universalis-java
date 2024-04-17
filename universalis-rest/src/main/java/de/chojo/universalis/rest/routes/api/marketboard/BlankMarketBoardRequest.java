/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
