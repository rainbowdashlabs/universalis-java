/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.Region;

import javax.annotation.CheckReturnValue;

/**
 * Interface for requests using regions
 *
 * @param <T> type of request builder
 */
public interface RegionScope<T> {
    /**
     * Region scope of the request.
     * <p>
     * Calling this again will remove other scopes.
     *
     * @param region region
     * @return request builder
     */
    @CheckReturnValue
    T region(Region region);
}
