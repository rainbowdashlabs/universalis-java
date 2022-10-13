/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.extra;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.ContentResponse;

public class EntityRequest extends RequestBuilder<ContentResponse> {
    public EntityRequest(UniversalisRestImpl xivapi) {
        super(xivapi, ContentResponse.class);
        path("extra", "content");
    }
}
