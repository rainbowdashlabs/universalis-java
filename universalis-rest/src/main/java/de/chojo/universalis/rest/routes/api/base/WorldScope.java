/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.World;

import org.jetbrains.annotations.CheckReturnValue;

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
