/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.routes.api.extra.BlankContentRequest;
import de.chojo.universalis.rest.routes.requests.extra.stats.Stats;

public class Extra {
    private final UniversalisRestImpl rest;
    private final Stats stats;

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
    public BlankContentRequest content() {
        return new ContentRequestImpl(rest);
    }

    public Stats stats() {
        return stats;
    }
}
