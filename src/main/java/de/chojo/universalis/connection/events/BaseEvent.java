/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events;

import com.fasterxml.jackson.annotation.JsonCreator;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.items.NameSupplier;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

public class BaseEvent {
    int item;
    int world;

    @JsonCreator
    public BaseEvent(int item, int world) {
        this.item = item;
        this.world = world;
    }

    public int itemId() {
        return item;
    }

    public int worldId() {
        return world;
    }

    public World world() {
        return Worlds.byId(worldId());
    }

    public Item item(NameSupplier supplier) {
        return Item.build(supplier, itemId());
    }
}
