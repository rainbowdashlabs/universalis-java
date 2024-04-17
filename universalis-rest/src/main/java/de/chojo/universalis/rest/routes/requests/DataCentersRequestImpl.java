/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;

/**
 * Implementation for a {@link DataCentersRequest}
 */
public class DataCentersRequestImpl extends RequestBuilder<DataCentersResponse> implements DataCentersRequest {
    /**
     * Create a new data centers request
     *
     * @param rest rest client
     */
    public DataCentersRequestImpl(UniversalisRestImpl rest) {
        super(rest, DataCentersResponse.class);
        path("data-centers");
    }
}
