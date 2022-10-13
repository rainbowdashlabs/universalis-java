/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.sales.impl;

import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.events.sales.SalesEvent;

import java.util.List;

/**
 * Represents addition of added sales.
 * <p>
 * This date is deduplicated and will only contain new sales entries.
 */
public class SalesAddEvent extends SalesEvent {
    public SalesAddEvent(World world, Item item, List<Sale> sales) {
        super(world, item, sales);
    }
}
