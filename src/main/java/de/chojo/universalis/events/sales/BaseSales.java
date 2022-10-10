/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.sales;

import de.chojo.universalis.entities.shared.Item;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.entities.shared.World;

import java.util.List;

public abstract class BaseSales extends Event {
    private final List<Sale> sales;

    public BaseSales(World world, Item item, List<Sale> sales) {
        super(world, item);
        this.sales = sales;
    }

    public List<Sale> sales() {
        return sales;
    }
}
