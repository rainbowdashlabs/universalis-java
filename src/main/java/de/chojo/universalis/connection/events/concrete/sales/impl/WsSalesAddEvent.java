/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.connection.events.concrete.sales.WsSalesEvent;
import de.chojo.universalis.connection.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.provider.NameSupplier;

import java.util.List;

/**
 * A websocket sales add event.
 */
public class WsSalesAddEvent extends WsSalesEvent<SalesAddEvent> {

    public WsSalesAddEvent(@JsonProperty("item") int item,
                           @JsonProperty("world") int world,
                           @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    @Override
    public SalesAddEvent toEvent(NameSupplier itemNames) {
        return new SalesAddEvent(world(), item(itemNames), toSales(world()));
    }
}
