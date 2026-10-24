/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.aggregated;

import de.chojo.universalis.rest.routes.api.AggregatedRequest;
import de.chojo.universalis.rest.routes.api.base.MultiItemScope;

/**
 * An aggregated request with a scope, waiting for its items.
 */
public interface ScopedAggregatedRequest extends MultiItemScope<AggregatedRequest> {}
