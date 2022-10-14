/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
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
