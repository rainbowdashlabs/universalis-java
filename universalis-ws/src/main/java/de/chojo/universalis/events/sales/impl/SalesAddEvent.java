/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.events.sales.impl;

import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.sales.SalesEvent;

import java.util.List;

/**
 * Represents addition of added sales.
 * <p>
 * This date is deduplicated and will only contain new sales entries.
 */
public class SalesAddEvent extends SalesEvent {
    /**
     * Create a new sales add event.
     * @param world world
     * @param item item
     * @param sales sales
     */
    public SalesAddEvent(World world, Item item, List<Sale> sales) {
        super(world, item, sales);
    }
}
