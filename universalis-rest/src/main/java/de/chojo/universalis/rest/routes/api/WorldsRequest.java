/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.rest.routes.requests.WorldsRequestImpl;

/**
 * Base implementation for a {@link WorldsRequestImpl}
 */
public interface WorldsRequest extends Request<WorldsResponse> {
}
