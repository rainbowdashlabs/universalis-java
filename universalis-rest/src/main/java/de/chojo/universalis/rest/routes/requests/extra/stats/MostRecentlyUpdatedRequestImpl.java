/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.MostRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.MostRecentlyUpdatedRequest;
import de.chojo.universalis.worlds.World;

import javax.annotation.CheckReturnValue;

/**
 * Implementation for a {@link MostRecentlyUpdatedRequest}
 */
public class MostRecentlyUpdatedRequestImpl extends RequestBuilder<MostRecentlyUpdatedResponse> implements MostRecentlyUpdatedRequest {
    /**
     * Create a new most restently updated request
     *
     * @param rest rest client
     */
    public MostRecentlyUpdatedRequestImpl(UniversalisRestImpl rest) {
        super(rest, MostRecentlyUpdatedResponse.class);
        path("extra", "stats", "most-recently-updated");
    }

    @Override
    @CheckReturnValue
    public MostRecentlyUpdatedRequest world(World world) {
        parameter("world", world.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public MostRecentlyUpdatedRequest dataCenter(de.chojo.universalis.worlds.DataCenter dataCenter) {
        parameter("dcName", dataCenter.name());
        return this;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Default 50, Max 200
     */
    @Override
    @CheckReturnValue
    public MostRecentlyUpdatedRequest limit(int limit) {
        parameter("entries", Math.min(200, limit));
        return this;
    }
}
