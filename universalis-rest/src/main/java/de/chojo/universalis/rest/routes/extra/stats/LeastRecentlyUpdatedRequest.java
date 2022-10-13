/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.extra.stats;

import de.chojo.universalis.entities.World;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.extra.stats.LeastRecentlyUpdatedResponse;

public class LeastRecentlyUpdatedRequest extends RequestBuilder<LeastRecentlyUpdatedResponse> {
    public LeastRecentlyUpdatedRequest(UniversalisRestImpl xivapi) {
        super(xivapi, LeastRecentlyUpdatedResponse.class);
        path("extra", "stats", "least-recently-updated");
    }

    /**
     * The world to request data for.
     *
     * @param world world
     * @return request
     */
    public LeastRecentlyUpdatedRequest world(World world) {
        parameter("world", world.id());
        return this;
    }

    /**
     * The data center to request data for.
     *
     * @param dataCenter datacenter
     * @return request
     */
    public LeastRecentlyUpdatedRequest dataCenter(DataCenter dataCenter) {
        parameter("dcName", dataCenter.name());
        return this;
    }

    /**
     * The number of entries to return (default 50, max 200).
     *
     * @param limit limit
     * @return request
     */
    public LeastRecentlyUpdatedRequest limit(int limit) {
        parameter("entries", Math.min(200, limit));
        return this;
    }
}
