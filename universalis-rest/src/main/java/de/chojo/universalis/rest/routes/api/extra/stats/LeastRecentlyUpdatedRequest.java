/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.stats.LeastRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.base.LimitedRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.leastrecentlyupdated.BlankLeastRecentlyUpdatedRequest;
import de.chojo.universalis.rest.routes.requests.extra.stats.LeastRecentlyUpdatedRequestImpl;

/**
 * Base implementation for a {@link LeastRecentlyUpdatedRequestImpl}
 */
public interface LeastRecentlyUpdatedRequest extends Request<LeastRecentlyUpdatedResponse>, BlankLeastRecentlyUpdatedRequest, LimitedRequest<LeastRecentlyUpdatedRequest> {
}
