/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.RecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.RecentlyUpdatedRequest;

/**
 * Implementation for a {@link RecentlyUpdatedRequest}
 */
public class RecentlyUpdatedRequestImpl extends RequestBuilder<RecentlyUpdatedResponse>
        implements RecentlyUpdatedRequest {
    /**
     * Create a new recently updated request
     *
     * @param rest rest client
     */
    public RecentlyUpdatedRequestImpl(UniversalisRestImpl rest) {
        super(rest, RecentlyUpdatedResponse.class);
        path("extra", "stats", "recently-updated");
    }
}
