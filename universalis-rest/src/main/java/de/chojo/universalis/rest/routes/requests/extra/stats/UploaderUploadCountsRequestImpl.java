/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.UploaderUploadCountsRequest;

/**
 * Implementation for a {@link UploaderUploadCountsRequest}
 */
public class UploaderUploadCountsRequestImpl extends RequestBuilder<UploaderUploadCountResponse> implements UploaderUploadCountsRequest {
    /**
     * Create a new uploader upload count request
     *
     * @param rest rest client
     */
    public UploaderUploadCountsRequestImpl(UniversalisRestImpl rest) {
        super(rest, UploaderUploadCountResponse.class);
        path("extra", "stats", "uploader-upload-counts");
    }
}
