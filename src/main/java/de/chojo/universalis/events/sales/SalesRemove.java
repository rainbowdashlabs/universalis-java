/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.events.sales;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import java.util.List;

public class SalesRemove extends BaseSales{
    public SalesRemove(World world, Item item, List<Sale> sales) {
        super(world, item, sales);
    }
}
