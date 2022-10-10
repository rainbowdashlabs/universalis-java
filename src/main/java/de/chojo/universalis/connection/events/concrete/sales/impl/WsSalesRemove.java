/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.sales.WsSalesBase;
import de.chojo.universalis.connection.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.impl.SalesRemove;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

public class WsSalesRemove extends WsSalesBase {
    public WsSalesRemove(@JsonProperty("item") int item,
                         @JsonProperty("world") int world,
                         @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    public SalesRemove toEvent(NameSupplier items) {
        return new SalesRemove(world(), item(items), toSales(world()));
    }
}
