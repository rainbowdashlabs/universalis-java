/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats.leastrecentlyupdated;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.routes.api.extra.stats.LeastRecentlyUpdatedRequest;

public interface BlankLeastRecentlyUpdatedRequest {
    LeastRecentlyUpdatedRequest world(World world);

    LeastRecentlyUpdatedRequest dataCenter(DataCenter dataCenter);
}
