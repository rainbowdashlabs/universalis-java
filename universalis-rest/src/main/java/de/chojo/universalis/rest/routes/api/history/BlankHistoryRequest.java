/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.history;

import de.chojo.universalis.rest.routes.api.base.DataCenterScope;
import de.chojo.universalis.rest.routes.api.base.RegionScope;
import de.chojo.universalis.rest.routes.api.base.WorldScope;

/**
 * Base for a {@link RegionHistoryRequest}
 */
public interface BlankHistoryRequest extends RegionScope<RegionHistoryRequest>, DataCenterScope<RegionHistoryRequest>, WorldScope<RegionHistoryRequest> {
}
