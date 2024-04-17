/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import javax.annotation.CheckReturnValue;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Implementation for a {@link MarketBoardRequest}
 */
public class MarketBoardRequestImpl extends RequestBuilder<MarketBoardResponse> implements MarketBoardRequest {

    /**
     * Create a new market board request
     *
     * @param rest rest client
     */
    public MarketBoardRequestImpl(UniversalisRestImpl rest) {
        super(rest, MarketBoardResponse.class);
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest region(Region region) {
        path(region.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest dataCenter(DataCenter dataCenter) {
        path(dataCenter.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest world(World world) {
        path(world.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest items(Item... items) {
        path(Arrays.stream(items).map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest items(Collection<Item> items) {
        path(items.stream().map(Item::id).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest itemsIds(Integer... itemIds) {
        path(Arrays.stream(itemIds).map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest itemsIds(Collection<Integer> itemIds) {
        path(itemIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest listingsLimit(int limit) {
        parameter("listings", limit);
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest historyLimit(int limit) {
        parameter("entries", limit);
        return this;
    }

    @Override
    @CheckReturnValue
    @Deprecated(forRemoval = true)
    public MarketBoardRequest noGst() {
        return noGst(true);
    }

    @Override
    @CheckReturnValue
    @Deprecated(forRemoval = true)
    public MarketBoardRequest noGst(boolean noGst) {
        parameter("noGst", noGst);
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest highQuality() {
        return highQuality(true);
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest highQuality(boolean highQuality) {
        parameter("hq", highQuality);
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest historyTime(Duration duration) {
        parameter("entriesWithin", Math.abs(duration.getSeconds()));
        return this;
    }

    @Override
    @CheckReturnValue
    public MarketBoardRequest statsTime(Duration duration) {
        parameter("statsWithin", duration.toMillis());
        return this;
    }
}
