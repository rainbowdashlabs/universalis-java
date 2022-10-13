/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;

public class DataCentersRequestImpl extends RequestBuilder<DataCentersResponse> implements DataCentersRequest {
    public DataCentersRequestImpl(UniversalisRestImpl xivapi) {
        super(xivapi, DataCentersResponse.class);
        path("data-centers");
    }
}
