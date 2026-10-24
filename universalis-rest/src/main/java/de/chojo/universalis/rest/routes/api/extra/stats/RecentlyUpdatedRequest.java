/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.stats.RecentlyUpdatedResponse;

/**
 * Request for some of the items updated most recently on any world. Universalis calls this a legacy endpoint.
 */
public interface RecentlyUpdatedRequest extends Request<RecentlyUpdatedResponse> {}
