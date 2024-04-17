/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.ContentResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.content.BlankContentRequest;
import de.chojo.universalis.rest.routes.requests.extra.ContentRequestImpl;

/**
 * Base implementation for a {@link ContentRequestImpl}
 */
public interface ContentRequest extends Request<ContentResponse>, BlankContentRequest {
}
