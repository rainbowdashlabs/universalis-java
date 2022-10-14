/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
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
