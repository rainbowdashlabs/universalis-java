/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.DataCenter;

import org.jetbrains.annotations.CheckReturnValue;

/**
 * Interface for requests using data centers
 *
 * @param <T> type of request builder
 */
public interface DataCenterScope<T> {
    /**
     * Data center scope of the request.
     * <p>
     * Calling this again will remove other scopes.
     *
     * @param dataCenter data center
     * @return request builder
     */
    @CheckReturnValue
    T dataCenter(DataCenter dataCenter);
}
