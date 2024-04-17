/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.websocket.events.concrete.sales.WsSalesEvent;
import de.chojo.universalis.websocket.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * A websocket sales remove event.
 */
public class WsSalesRemoveEvent extends WsSalesEvent<SalesRemoveEvent> {
    /**
     * Creates a new websocket sales remove event
     * @param item item id
     * @param world world
     * @param saleViews sale views
     */
    public WsSalesRemoveEvent(@JsonProperty("item") Item item,
                              @JsonProperty("world") World world,
                              @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    @Override
    public SalesRemoveEvent toEvent() {
        return new SalesRemoveEvent(world(), item(), toSales(world()));
    }
}
