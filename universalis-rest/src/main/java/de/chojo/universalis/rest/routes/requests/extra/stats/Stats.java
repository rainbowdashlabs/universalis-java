/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.routes.api.extra.WorldUploadCountsRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.UploadHistoryRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.UploaderUploadCountsRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.leastrecentlyupdated.BlankLeastRecentlyUpdatedRequest;
import de.chojo.universalis.rest.routes.api.extra.stats.mostrecentlyupdated.BlankMostRecentlyUpdatedRequest;

import javax.annotation.CheckReturnValue;

/**
 * Class providing access to stats requests
 */
public class Stats {
    private final UniversalisRestImpl rest;

    /**
     * Create a new stat route
     *
     * @param rest rest client
     */
    public Stats(UniversalisRestImpl rest) {
        this.rest = rest;
    }

    /**
     * Get the least-recently updated items on the specified world or data center, along with the upload times for each item.
     *
     * @return request builder
     */
    @CheckReturnValue
    public BlankLeastRecentlyUpdatedRequest leastRecentlyUpdated() {
        return new LeastRecentlyUpdatedRequestImpl(rest);
    }

    /**
     * Get the most-recently updated items on the specified world or data center, along with the upload times for each item.
     *
     * @return request builder
     */
    @CheckReturnValue
    public BlankMostRecentlyUpdatedRequest mostRecentlyUpdated() {
        return new MostRecentlyUpdatedRequestImpl(rest);
    }

    /**
     * Returns the total upload counts for each client application that uploads data to Universalis.
     *
     * @return request builder
     */
    @CheckReturnValue
    public UploaderUploadCountsRequest uploaderUploadCounts() {
        return new UploaderUploadCountsRequestImpl(rest);
    }

    /**
     * Returns the world upload counts and proportions of the total uploads for each world.
     *
     * @return request builder
     */
    @CheckReturnValue
    public WorldUploadCountsRequest worldUploadCounts() {
        return new WorldUploadCountsRequestImpl(rest);
    }

    /**
     * Returns the number of uploads per day over the past 30 days.
     *
     * @return request builder
     */
    @CheckReturnValue
    public UploadHistoryRequest uploadHistory() {
        return new UploadHistoryRequestImpl(rest);
    }
}
