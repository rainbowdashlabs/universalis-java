/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.stats.UploadHistoryResponse;
import de.chojo.universalis.rest.routes.requests.extra.stats.UploadHistoryRequestImpl;

/**
 * Base implementation for a {@link UploadHistoryRequestImpl}
 */
public interface UploadHistoryRequest extends Request<UploadHistoryResponse> {
}
