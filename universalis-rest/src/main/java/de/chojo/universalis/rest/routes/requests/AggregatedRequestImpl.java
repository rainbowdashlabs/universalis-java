/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.UniversalisRestImpl;
import de.chojo.universalis.rest.requests.RequestBuilder;
import de.chojo.universalis.rest.response.AggregatedResponse;
import de.chojo.universalis.rest.routes.api.AggregatedRequest;
import de.chojo.universalis.rest.routes.api.aggregated.BlankAggregatedRequest;
import de.chojo.universalis.rest.routes.api.aggregated.ScopedAggregatedRequest;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;
import org.jetbrains.annotations.CheckReturnValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Implementation for an {@link AggregatedRequest}
 */
public class AggregatedRequestImpl extends RequestBuilder<AggregatedResponse>
        implements BlankAggregatedRequest, ScopedAggregatedRequest, AggregatedRequest {
    private static final int MAX_ITEMS = 100;

    /**
     * Create a new aggregated request
     *
     * @param rest rest client
     */
    public AggregatedRequestImpl(UniversalisRestImpl rest) {
        super(rest, AggregatedResponse.class);
        path("aggregated");
    }

    @Override
    @CheckReturnValue
    public ScopedAggregatedRequest region(Region region) {
        markScope("region");
        path(region.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public ScopedAggregatedRequest dataCenter(DataCenter dataCenter) {
        markScope("dataCenter");
        path(dataCenter.name());
        return this;
    }

    @Override
    @CheckReturnValue
    public ScopedAggregatedRequest world(World world) {
        markScope("world");
        path(world.id());
        return this;
    }

    @Override
    @CheckReturnValue
    public AggregatedRequest items(Item... items) {
        itemsPath(Arrays.stream(items).map(Item::id).toList(), MAX_ITEMS);
        return this;
    }

    @Override
    @CheckReturnValue
    public AggregatedRequest items(Collection<Item> items) {
        itemsPath(items.stream().map(Item::id).toList(), MAX_ITEMS);
        return this;
    }

    @Override
    @CheckReturnValue
    public AggregatedRequest itemsIds(Integer... itemIds) {
        itemsPath(Arrays.asList(itemIds), MAX_ITEMS);
        return this;
    }

    @Override
    @CheckReturnValue
    public AggregatedRequest itemsIds(Collection<Integer> itemIds) {
        itemsPath(List.copyOf(itemIds), MAX_ITEMS);
        return this;
    }
}
