/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.events;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;

/**
 * Base class for an event.
 */
public class Event {
    private final World world;
    private final Item item;

    /**
     * Creates a new event
     *
     * @param world world
     * @param item  item
     */
    public Event(World world, Item item) {
        this.world = world;
        this.item = item;
    }

    /**
     * Event world
     *
     * @return world
     */
    public World world() {
        return world;
    }

    /**
     * Event item
     *
     * @return item
     */
    public Item item() {
        return item;
    }
}
