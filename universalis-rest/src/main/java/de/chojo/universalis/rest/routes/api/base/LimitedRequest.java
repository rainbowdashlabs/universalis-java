/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import javax.annotation.CheckReturnValue;

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
