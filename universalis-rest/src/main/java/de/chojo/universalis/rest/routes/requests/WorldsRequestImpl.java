/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
