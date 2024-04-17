/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import de.chojo.universalis.rest.routes.api.extra.WorldUploadCountsRequest;

/**
 * Implementation for a {@link WorldUploadCountsRequest}
 */
public class WorldUploadCountsRequestImpl extends RequestBuilder<WorldUploadCountResponse> implements WorldUploadCountsRequest {
    /**
     * Create a new world upload count request
     *
     * @param rest rest client
     */
    public WorldUploadCountsRequestImpl(UniversalisRestImpl rest) {
        super(rest, WorldUploadCountResponse.class);
        path("extra", "stats", "world-upload-counts");
    }
}
