/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.subscriber;

import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.WorldProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class implementing a subscription for a channel.
 * <p>
 * Allows setting world filters and defined the subscribed channel
 *
 * @see Subscriptions
 */
public class Subscription {
    private final String channel;
    private final List<World> worlds = new ArrayList<>();

    protected Subscription(String channel) {
        this.channel = channel;
    }

    /**
     * True when the subscription is restricted to worlds.
     *
     * @return true when restricted
     */
    public boolean hasWorlds() {
        return !worlds.isEmpty();
    }

    /**
     * Gets a list of all channels this subscription represents
     *
     * @return list of channel
     */
    public List<String> channel() {
        if (hasWorlds()) {
            return worlds.stream().map(w -> "%s{world=%d}".formatted(channel, w.id())).toList();
        }
        return Collections.singletonList(channel);
    }

    /**
     * Adds a world filter. Already set filters persist.
     *
     * @param world world to add
     * @return subcription instance for chaining.
     */
    public Subscription restrict(World world) {
        return provideWorld(world);
    }

    /**
     * Adds all worlds from a datacenter as a filter. Already set filters persist.
     *
     * @param datacenter datacenter to add
     * @return subcription instance for chaining.
     */
    public Subscription restrict(DataCenter datacenter) {
        return provideWorld(datacenter);
    }

    /**
     * Adds all worlds from a region as a filter. Already set filters persist.
     *
     * @param region region to add
     * @return subcription instance for chaining.
     */
    public Subscription forRegion(Region region) {
        return provideWorld(region);
    }

    private Subscription provideWorld(WorldProvider worldProvider) {
        worlds.addAll(worldProvider.worlds());
        return this;
    }
}
