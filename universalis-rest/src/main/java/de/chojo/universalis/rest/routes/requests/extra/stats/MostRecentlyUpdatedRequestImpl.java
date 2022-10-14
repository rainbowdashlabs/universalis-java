/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.MostRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.MostRecentlyUpdatedRequest;

public class MostRecentlyUpdatedRequestImpl extends RequestBuilder<MostRecentlyUpdatedResponse> implements MostRecentlyUpdatedRequest {
    public MostRecentlyUpdatedRequestImpl(UniversalisRestImpl xivapi) {
        super(xivapi, MostRecentlyUpdatedResponse.class);
        path("extra", "stats", "most-recently-updated");
    }

    /**
     * The world to request data for.
     *
     * @param world world
     * @return request
     */
    @Override
    public MostRecentlyUpdatedRequest world(World world) {
        parameter("world", world.id());
        return this;
    }

    /**
     * The data center to request data for.
     *
     * @param dataCenter datacenter
     * @return request
     */
    @Override
    public MostRecentlyUpdatedRequest dataCenter(DataCenter dataCenter) {
        parameter("dcName", dataCenter.name());
        return this;
    }

    /**
     * The number of entries to return (default 50, max 200).
     *
     * @param limit limit
     * @return request
     */
    @Override
    public MostRecentlyUpdatedRequest limit(int limit) {
        parameter("entries", Math.min(200, limit));
        return this;
    }
}
