/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
