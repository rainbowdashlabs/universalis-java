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
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class MarketBoardRequestImpl extends RequestBuilder<MarketBoardResponse> implements MarketBoardRequest {

    public MarketBoardRequestImpl(UniversalisRestImpl rest) {
        super(rest, MarketBoardResponse.class);
    }

    @Override
    public MarketBoardRequestImpl region(Region region) {
        path(region.name());
        return this;
    }

    @Override
    public MarketBoardRequestImpl dataCenter(DataCenter dataCenter) {
        path(dataCenter.id());
        return this;
    }

    @Override
    public MarketBoardRequestImpl world(World world) {
        path(world.id());
        return this;
    }

    @Override
    public MarketBoardRequestImpl items(Item... items) {
        path(Arrays.stream(items).map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public MarketBoardRequestImpl items(Collection<Item> items) {
        path(items.stream().map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public MarketBoardRequestImpl itemsIds(Integer... itemIds) {
        path(Arrays.stream(itemIds).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public MarketBoardRequestImpl itemsIds(Collection<Integer> itemIds) {
        path(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    public MarketBoardRequestImpl listingsLimit(int limit) {
        parameter("listings", limit);
        return this;
    }

    @Override
    public MarketBoardRequestImpl historyLimit(int limit) {
        parameter("entries", limit);
        return this;
    }

    @Override
    public MarketBoardRequestImpl noGst() {
        return noGst(true);
    }

    @Override
    public MarketBoardRequestImpl noGst(boolean noGst) {
        parameter("noGst", noGst);
        return this;
    }

    @Override
    public MarketBoardRequestImpl highQuality() {
        return highQuality(true);
    }

    @Override
    public MarketBoardRequestImpl highQuality(boolean highQuality) {
        parameter("hq", highQuality);
        return this;
    }

    @Override
    public MarketBoardRequestImpl historyTime(Duration duration) {
        parameter("entriesWithin", Math.abs(duration.getSeconds()));
        return this;
    }

    @Override
    public MarketBoardRequest statsTime(Duration duration) {
        parameter("statsWithin", duration.toMillis());
        return this;
    }
}
