/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats.mostrecentlyupdated;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.routes.api.extra.stats.MostRecentlyUpdatedRequest;

public interface BlankMostRecentlyUpdatedRequest {
    MostRecentlyUpdatedRequest world(World world);

    MostRecentlyUpdatedRequest dataCenter(DataCenter dataCenter);
}
