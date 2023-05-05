/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.sales.impl;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.provider.items.Items;
import de.chojo.universalis.websocket.events.concrete.sales.WsSalesEvent;
import de.chojo.universalis.websocket.events.concrete.sales.views.SaleView;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * A websocket sales add event.
 */
public class WsSalesAddEvent extends WsSalesEvent<SalesAddEvent> {

    /**
     * Creates a new websocket sales add event
     * @param item item id
     * @param world world id
     * @param saleViews sale views
     */
    public WsSalesAddEvent(@JsonProperty("item") Item item,
                           @JsonProperty("world") World world,
                           @JsonProperty("sales") List<SaleView> saleViews) {
        super(item, world, saleViews);
    }

    @Override
    public SalesAddEvent toEvent() {
        return new SalesAddEvent(world(), item(), toSales(world()));
    }
}
