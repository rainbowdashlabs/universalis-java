/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats.mostrecentlyupdated;

import de.chojo.universalis.rest.routes.api.base.DataCenterScope;
import de.chojo.universalis.rest.routes.api.base.WorldScope;
import de.chojo.universalis.rest.routes.api.extra.stats.MostRecentlyUpdatedRequest;

/**
 * Base implementation for a {@link MostRecentlyUpdatedRequest}
 */
public interface BlankMostRecentlyUpdatedRequest extends DataCenterScope<MostRecentlyUpdatedRequest>, WorldScope<MostRecentlyUpdatedRequest> {
}
