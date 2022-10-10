/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events;

import de.chojo.universalis.entities.shared.Item;
import de.chojo.universalis.entities.shared.World;

public class Event {
    private final World world;
    private final Item item;

    public Event(World world, Item item) {
        this.world = world;
        this.item = item;
    }

    public World world() {
        return world;
    }

    public Item item() {
        return item;
    }
}
