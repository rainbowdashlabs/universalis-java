/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.rest.routes.api.WorldsRequest;

public class WorldsRequestImpl extends RequestBuilder<WorldsResponse> implements WorldsRequest {
    public WorldsRequestImpl(UniversalisRestImpl xivapi) {
        super(xivapi, WorldsResponse.class);
        path("worlds");
    }
}
