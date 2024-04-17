/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
