/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.aggregated;

import de.chojo.universalis.rest.routes.api.base.DataCenterScope;
import de.chojo.universalis.rest.routes.api.base.RegionScope;
import de.chojo.universalis.rest.routes.api.base.WorldScope;

/**
 * An aggregated request without a scope.
 */
public interface BlankAggregatedRequest
        extends RegionScope<ScopedAggregatedRequest>,
                DataCenterScope<ScopedAggregatedRequest>,
                WorldScope<ScopedAggregatedRequest> {}
