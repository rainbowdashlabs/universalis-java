/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.sales.WsSalesBase;
import de.chojo.universalis.connection.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.SalesAdd;
import de.chojo.universalis.items.NameSupplier;

import java.util.List;

public class WsSalesAdd extends WsSalesBase {

    public WsSalesAdd(@JsonProperty("item") int item,
                      @JsonProperty("world") int world,
                      @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    public SalesAdd toEvent(NameSupplier items) {
        return new SalesAdd(world(), item(items), toSales(world()));
    }
}
