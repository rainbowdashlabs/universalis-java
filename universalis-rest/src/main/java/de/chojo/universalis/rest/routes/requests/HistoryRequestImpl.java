/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.routes.api.HistoryRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class HistoryRequestImpl extends RequestBuilder<HistoryResponse> implements HistoryRequest {

    public HistoryRequestImpl(UniversalisRestImpl rest) {
        super(rest, HistoryResponse.class);
        path("history");
    }

    @Override
    public HistoryRequestImpl region(Region region) {
        path(region.name());
        return this;
    }

    @Override
    public HistoryRequestImpl dataCenter(DataCenter dataCenter) {
        path(dataCenter.id());
        return this;
    }

    @Override
    public HistoryRequestImpl world(World world) {
        path(world.id());
        return this;
    }

    @Override
    public HistoryRequestImpl items(Item... items) {
        path(Arrays.stream(items).map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public HistoryRequestImpl items(Collection<Item> items) {
        path(items.stream().map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public HistoryRequestImpl itemsIds(Integer... itemIds) {
        path(Arrays.stream(itemIds).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public HistoryRequestImpl itemsIds(Collection<Integer> itemIds) {
        path(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public HistoryRequest limit(int limit) {
        parameter("entriesToReturn", limit);
        return this;
    }

    @Override
    public HistoryRequest historyTime(Duration duration) {
        parameter("entriesWithin", Math.abs(duration.getSeconds()));
        return this;
    }

    @Override
    public HistoryRequest statsTime(Duration duration) {
        parameter("statsWithin", duration.toMillis());
        return this;
    }
}
