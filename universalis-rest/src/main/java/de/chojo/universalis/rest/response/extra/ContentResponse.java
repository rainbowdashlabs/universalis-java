/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra;

import de.chojo.universalis.rest.routes.api.extra.ContentRequest;

import java.util.Map;

/**
 * Response for a {@link ContentRequest}
 *
 * @param content content
 */
//TODO: Probably a no fix. Dunno what it does and I dont need the data as it seems ¯\_(ツ)_/¯
public record ContentResponse(Map<String, Object> content) {
}
