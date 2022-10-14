/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.LeastRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.LeastRecentlyUpdatedRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.World;

import javax.annotation.CheckReturnValue;

/**
 * Implementation for a {@link LeastRecentlyUpdatedRequest}
 */
public class LeastRecentlyUpdatedRequestImpl extends RequestBuilder<LeastRecentlyUpdatedResponse> implements LeastRecentlyUpdatedRequest {
    /**
     * Create a new least recently updated request
     *
     * @param rest rest client
     */
    public LeastRecentlyUpdatedRequestImpl(UniversalisRestImpl rest) {
        super(rest, LeastRecentlyUpdatedResponse.class);
        path("extra", "stats", "least-recently-updated");
    }

    @Override
    @CheckReturnValue
    public LeastRecentlyUpdatedRequest world(World world) {
        parameter("world", world.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public LeastRecentlyUpdatedRequest dataCenter(DataCenter dataCenter) {
        parameter("dcName", dataCenter.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public LeastRecentlyUpdatedRequest limit(int limit) {
        parameter("entries", Math.min(200, limit));
        return this;
    }
}
