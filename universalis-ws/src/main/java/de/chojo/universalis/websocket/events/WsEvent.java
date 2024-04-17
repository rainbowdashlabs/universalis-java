/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.worlds.World;

/**
 * A websocket event.
 */
public abstract class WsEvent<T extends Event> implements EventSupplier<T> {
    private final Item item;
    private final World world;

    /**
     * Create a new websocket event.
     *
     * @param item  item
     * @param world world
     */
    public WsEvent(Item item, World world) {
        this.item = item;
        this.world = world;
    }

    /**
     * The id of the event item
     *
     * @return item id
     */
    public Item item() {
        return item;
    }

    /**
     * The id of the event world
     *
     * @return world id
     */
    public World world() {
        return world;
    }
}
