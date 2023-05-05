/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.UploadHistoryResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.UploadHistoryRequest;

/**
 * Implementation for a {@link UploadHistoryRequest}
 */
public class UploadHistoryRequestImpl extends RequestBuilder<UploadHistoryResponse> implements UploadHistoryRequest {
    /**
     * Create a new upload history request
     *
     * @param rest rest client
     */
    public UploadHistoryRequestImpl(UniversalisRestImpl rest) {
        super(rest, UploadHistoryResponse.class);
        path("extra", "stats", "upload-history");
    }
}
