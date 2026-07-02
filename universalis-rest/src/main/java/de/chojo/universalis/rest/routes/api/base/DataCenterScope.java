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
     * Data center scope of the request. Scopes are mutually exclusive — calling this
     * after another scope method throws an {@link IllegalStateException}.
     *
     * @param dataCenter data center
     * @return request builder
     */
    @CheckReturnValue
    T dataCenter(DataCenter dataCenter);
}
