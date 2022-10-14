/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.extra.ContentResponse;

public interface ContentRequest extends Request<ContentResponse>, BlankContentRequest {
}
