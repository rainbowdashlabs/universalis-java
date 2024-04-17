/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.sales;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * Represents a sales event.
 */
public abstract class SalesEvent extends Event {
    private final List<Sale> sales;

    /**
     * Creates a new sales event
     * @param world world
     * @param item item
     * @param sales sales
     */
    public SalesEvent(World world, Item item, List<Sale> sales) {
        super(world, item);
        this.sales = sales;
    }

    /**
     * Sales of this event
     * @return unmodifiable list
     */
    public List<Sale> sales() {
        return sales;
    }
}
