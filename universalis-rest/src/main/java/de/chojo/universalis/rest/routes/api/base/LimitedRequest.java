/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import org.jetbrains.annotations.CheckReturnValue;

/**
 * Interface for requests using limits
 *
 * @param <T> type of request builder
 */
public interface LimitedRequest<T> {
    /**
     * Set the limit of retrieved entries for this request
     *
     * @param limit limit
     * @return request builder
     */
    @CheckReturnValue
    T limit(int limit);
}
