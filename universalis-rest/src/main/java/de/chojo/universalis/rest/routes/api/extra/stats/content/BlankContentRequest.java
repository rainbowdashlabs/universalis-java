/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.extra.stats.content;

import de.chojo.universalis.rest.routes.api.extra.ContentRequest;

/**
 * Base implementation for a {@link ContentRequest}
 */
public interface BlankContentRequest {
    /**
     * The content id
     *
     * @param id id
     * @return request builder
     */
    ContentRequest id(String id);
}
