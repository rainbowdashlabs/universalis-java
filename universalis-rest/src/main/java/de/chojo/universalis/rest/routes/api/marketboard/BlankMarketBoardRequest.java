/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.entities.World;
import de.chojo.universalis.worlds.Datacenter;
import de.chojo.universalis.worlds.Region;

public interface BlankMarketBoardRequest {
    RegionMarketBoardRequest region(Region region);

    RegionMarketBoardRequest dataCenter(Datacenter dataCenter);

    RegionMarketBoardRequest world(World world);
}
