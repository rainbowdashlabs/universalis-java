/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.rest.routes.api.WorldsRequest;

/**
 * Implementation for a {@link WorldsRequest}
 */
public class WorldsRequestImpl extends RequestBuilder<WorldsResponse> implements WorldsRequest {
    /**
     * Create a new worlds request
     *
     * @param rest rest client
     */
    public WorldsRequestImpl(UniversalisRestImpl rest) {
        super(rest, WorldsResponse.class);
        path("worlds");
    }
}
