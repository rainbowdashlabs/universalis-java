/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import de.chojo.universalis.rest.routes.requests.extra.stats.UploaderUploadCountsRequestImpl;

/**
 * Base implementation for a {@link UploaderUploadCountsRequestImpl}
 */
public interface UploaderUploadCountsRequest extends Request<UploaderUploadCountResponse> {
}
