/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;

public interface BlankMarketBoardRequest {
    RegionMarketBoardRequest region(Region region);

    RegionMarketBoardRequest dataCenter(DataCenter dataCenter);

    RegionMarketBoardRequest world(World world);
}
