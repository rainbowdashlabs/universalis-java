/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.worlds.Region;

import org.jetbrains.annotations.CheckReturnValue;

/**
 * Interface for requests using regions
 *
 * @param <T> type of request builder
 */
public interface RegionScope<T> {
    /**
     * Region scope of the request. Scopes are mutually exclusive — calling this
     * after another scope method throws an {@link IllegalStateException}.
     *
     * @param region region
     * @return request builder
     */
    @CheckReturnValue
    T region(Region region);
}
