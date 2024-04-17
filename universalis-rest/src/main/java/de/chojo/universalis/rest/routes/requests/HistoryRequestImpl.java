/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.routes.api.HistoryRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import javax.annotation.CheckReturnValue;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation for a {@link HistoryRequest}
 */
public class HistoryRequestImpl extends RequestBuilder<HistoryResponse> implements HistoryRequest {

    /**
     * Create a new history request
     *
     * @param rest rest client
     */
    public HistoryRequestImpl(UniversalisRestImpl rest) {
        super(rest, HistoryResponse.class);
        path("history");
    }

    @Override
    @CheckReturnValue
    public HistoryRequest region(Region region) {
        path(region.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest dataCenter(DataCenter dataCenter) {
        path(dataCenter.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest world(World world) {
        path(world.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest items(Item... items) {
        path(Arrays.stream(items).map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest items(Collection<Item> items) {
        path(items.stream().map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest itemsIds(Integer... itemIds) {
        path(Arrays.stream(itemIds).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest itemsIds(Collection<Integer> itemIds) {
        path(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest limit(int limit) {
        parameter("entriesToReturn", limit);
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest historyTime(Duration duration) {
        parameter("entriesWithin", Math.abs(duration.getSeconds()));
        return this;
    }

    @Override
    @CheckReturnValue
    public HistoryRequest statsTime(Duration duration) {
        parameter("statsWithin", duration.toMillis());
        return this;
    }
}
