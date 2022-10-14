/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.UploaderUploadCountsRequest;

public class UploaderUploadCountsRequestImpl extends RequestBuilder<UploaderUploadCountResponse> implements UploaderUploadCountsRequest {
    public UploaderUploadCountsRequestImpl(UniversalisRestImpl rest) {
        super(rest, UploaderUploadCountResponse.class);
        path("extra", "stats", "uploader-upload-counts");
    }
}
