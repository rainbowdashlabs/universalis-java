/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.sales.WsSalesEvent;
import de.chojo.universalis.connection.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

/**
 * A websocket sales remove event.
 */
public class WsSalesRemoveEvent extends WsSalesEvent<SalesRemoveEvent> {
    public WsSalesRemoveEvent(@JsonProperty("item") int item,
                              @JsonProperty("world") int world,
                              @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    @Override
    public SalesRemoveEvent toEvent(NameSupplier items) {
        return new SalesRemoveEvent(world(), item(items), toSales(world()));
    }
}
