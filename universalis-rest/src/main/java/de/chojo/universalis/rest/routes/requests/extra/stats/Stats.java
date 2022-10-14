/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.routes.api.extra.WorldUploadCountsRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.UploadHistoryRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.UploaderUploadCountsRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.leastrecentlyupdated.BlankLeastRecentlyUpdatedRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.mostrecentlyupdated.BlankMostRecentlyUpdatedRequest;

public class Stats {
    private final UniversalisRestImpl rest;

    public Stats(UniversalisRestImpl rest) {
        this.rest = rest;
    }

    /**
     * Get the least-recently updated items on the specified world or data center, along with the upload times for each item.
     *
     * @return request builder
     */
    public BlankLeastRecentlyUpdatedRequest leastRecentlyUpdated() {
        return new LeastRecentlyUpdatedRequestImpl(rest);
    }

    /**
     * Get the most-recently updated items on the specified world or data center, along with the upload times for each item.
     *
     * @return request builder
     */
    public BlankMostRecentlyUpdatedRequest mostRecentlyUpdated() {
        return new MostRecentlyUpdatedRequestImpl(rest);
    }

    /**
     * Returns the total upload counts for each client application that uploads data to Universalis.
     *
     * @return request builder
     */
    public UploaderUploadCountsRequest uploaderUploadCounts() {
        return new UploaderUploadCountsRequestImpl(rest);
    }

    /**
     * Returns the world upload counts and proportions of the total uploads for each world.
     *
     * @return request builder
     */
    public WorldUploadCountsRequest worldUploadCounts() {
        return new WorldUploadCountsRequestImpl(rest);
    }

    /**
     * Returns the number of uploads per day over the past 30 days.
     *
     * @return request builder
     */
    public UploadHistoryRequest uploadHistory() {
        return new UploadHistoryRequestImpl(rest);
    }
}
