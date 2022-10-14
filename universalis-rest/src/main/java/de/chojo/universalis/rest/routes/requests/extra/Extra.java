/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.routes.api.extra.stats.content.BlankContentRequest;
import de.chojo.universalis.rest.routes.requests.extra.stats.Stats;

import javax.annotation.CheckReturnValue;

/**
 * Class providing requests for the extra route
 */
public class Extra {
    private final UniversalisRestImpl rest;
    private final Stats stats;

    /**
     * Create a new extra route
     *
     * @param rest rest client
     */
    @CheckReturnValue
    public Extra(UniversalisRestImpl rest) {
        this.rest = rest;
        stats = new Stats(rest);
    }

    /**
     * This endpoint is not really implemented
     *
     * @return content request
     */
    @Deprecated
    @CheckReturnValue
    public BlankContentRequest content() {
        return new ContentRequestImpl(rest);
    }

    /**
     * Provides requests to stats route
     *
     * @return stats route
     */
    @CheckReturnValue
    public Stats stats() {
        return stats;
    }
}
