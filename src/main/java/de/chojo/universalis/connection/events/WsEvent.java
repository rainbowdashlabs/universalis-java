/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.chojo.universalis.connection.builder.UniversalisWsBuilder;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.worlds.Worlds;

/**
 * A websocket event.
 */
public abstract class WsEvent<T extends Event> implements EventSupplier<T> {
    private final int item;
    private final int world;

    @JsonCreator
    public WsEvent(int item, int world) {
        this.item = item;
        this.world = world;
    }

    /**
     * The id of the event item
     *
     * @return item id
     */
    public int itemId() {
        return item;
    }

    /**
     * The id of the event world
     *
     * @return world id
     */
    public int worldId() {
        return world;
    }

    /**
     * Maps the {@link #worldId()} to a world via the {@link Worlds} class
     *
     * @return world
     */
    public World world() {
        return Worlds.byId(worldId());
    }

    /**
     * Maps the {@link #itemId()} to an {@link Item} via the {@link NameSupplier} set in {@link UniversalisWsBuilder#itemNameSupplier(NameSupplier)}
     *
     * @param supplier supplier
     * @return item
     */
    public Item item(NameSupplier supplier) {
        return Item.build(supplier, itemId());
    }
}
