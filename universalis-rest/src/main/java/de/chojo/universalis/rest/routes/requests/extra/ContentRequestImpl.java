/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.ContentResponse;
import de.chojo.universalis.rest.routes.api.extra.ContentRequest;

import javax.annotation.CheckReturnValue;

/**
 * Implementation of a {@link ContentRequest}
 */
public class ContentRequestImpl extends RequestBuilder<ContentResponse> implements ContentRequest {
    /**
     * Create a new content request
     *
     * @param rest rest clietn
     */
    public ContentRequestImpl(UniversalisRestImpl rest) {
        super(rest, ContentResponse.class);
        path("extra", "content");
    }

    @Override
    @CheckReturnValue
    public ContentRequest id(String id) {
        path(id);
        return this;
    }
}
