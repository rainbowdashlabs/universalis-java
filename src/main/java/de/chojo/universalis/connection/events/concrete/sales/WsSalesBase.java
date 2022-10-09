/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.sales;

import de.chojo.universalis.connection.events.BaseEvent;
import de.chojo.universalis.connection.events.concrete.sales.views.SaleView;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import java.util.List;

public class WsSalesBase extends BaseEvent {

    private final List<SaleView> saleView;

    public WsSalesBase(int item, int world, List<SaleView> saleView) {
        super(item, world);
        this.saleView = saleView;
    }

    public List<Sale> toSales(World world) {
        return saleView.stream().map(view -> view.toSale(world)).toList();
    }
}
