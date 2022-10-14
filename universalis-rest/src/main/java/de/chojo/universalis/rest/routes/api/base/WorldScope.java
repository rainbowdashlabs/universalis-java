/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.World;

import javax.annotation.CheckReturnValue;

/**
 * Interface for requests using worlds
 *
 * @param <T> type of request builder
 */
public interface WorldScope<T> {
    /**
     * World scope of the request.
     * <p>
     * Calling this again will remove other scopes.
     *
     * @param world world
     * @return request builder
     */
    @CheckReturnValue
    T world(World world);
}
