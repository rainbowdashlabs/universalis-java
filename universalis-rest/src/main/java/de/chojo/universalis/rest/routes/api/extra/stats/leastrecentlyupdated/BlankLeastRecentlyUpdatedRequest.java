/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats.leastrecentlyupdated;

import de.chojo.universalis.rest.routes.api.base.DataCenterScope;
import de.chojo.universalis.rest.routes.api.base.WorldScope;
import de.chojo.universalis.rest.routes.api.extra.stats.LeastRecentlyUpdatedRequest;

/**
 * Base implementation for a {@link LeastRecentlyUpdatedRequest}
 */
public interface BlankLeastRecentlyUpdatedRequest extends DataCenterScope<LeastRecentlyUpdatedRequest>, WorldScope<LeastRecentlyUpdatedRequest> {
}
