/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.World;

/**
 * Base class for an event.
 */
public class Event {
    private final World world;
    private final Item item;

    public Event(World world, Item item) {
        this.world = world;
        this.item = item;
    }

    /**
     * Event world
     * @return world
     */
    public World world() {
        return world;
    }

    /**
     * Event item
     * @return item
     */
    public Item item() {
        return item;
    }
}
