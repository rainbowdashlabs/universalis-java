/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.DataCenter;

import javax.annotation.CheckReturnValue;

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
