/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.sales.impl;

import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.sales.SalesEvent;

import java.util.List;

/**
 * Represents removal of sales entries.
 *
 * @deprecated Sales do not get removed anymore.
 */
@Deprecated
public class SalesRemoveEvent extends SalesEvent {
    /**
     * Creates a new sales remove event
     * @param world world
     * @param item item
     * @param sales sales
     */
    public SalesRemoveEvent(World world, Item item, List<Sale> sales) {
        super(world, item, sales);
    }
}
