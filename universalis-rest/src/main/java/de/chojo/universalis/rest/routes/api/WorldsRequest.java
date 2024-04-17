/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
