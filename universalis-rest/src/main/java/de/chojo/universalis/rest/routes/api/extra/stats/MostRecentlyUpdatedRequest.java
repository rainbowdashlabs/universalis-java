/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.stats.MostRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.base.LimitedRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.mostrecentlyupdated.BlankMostRecentlyUpdatedRequest;
import de.chojo.universalis.rest.routes.requests.extra.stats.MostRecentlyUpdatedRequestImpl;

/**
 * Base implementation for a {@link MostRecentlyUpdatedRequestImpl}
 */
public interface MostRecentlyUpdatedRequest extends Request<MostRecentlyUpdatedResponse>, BlankMostRecentlyUpdatedRequest, LimitedRequest<MostRecentlyUpdatedRequest> {
}
