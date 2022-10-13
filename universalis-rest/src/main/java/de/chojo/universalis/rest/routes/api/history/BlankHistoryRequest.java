/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.history;

import de.chojo.universalis.entities.World;
import de.chojo.universalis.worlds.Datacenter;
import de.chojo.universalis.worlds.Region;

public interface BlankHistoryRequest {
    RegionHistoryRequest region(Region region);

    RegionHistoryRequest dataCenter(Datacenter dataCenter);

    RegionHistoryRequest world(World world);
}
