/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
