/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.routes.requests.DataCentersRequestImpl;

/**
 * Base implementation for a {@link DataCentersRequestImpl}
 */
public interface DataCentersRequest extends Request<DataCentersResponse> {
}
