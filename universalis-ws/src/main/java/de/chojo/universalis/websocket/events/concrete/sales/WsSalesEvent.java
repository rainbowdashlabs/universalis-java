/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.sales;

import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.websocket.events.concrete.sales.views.SaleView;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.events.Event;

import java.util.List;

/**
 * Class to map sales events
 */
public abstract class WsSalesEvent<T extends Event> extends WsEvent<T> {

    private final List<SaleView> saleView;

    public WsSalesEvent(int item, int world, List<SaleView> saleView) {
        super(item, world);
        this.saleView = saleView;
    }

    /**
     * Converts the {@link SaleView} to a {@link Sale} object.
     *
     * @param world world for the sale
     * @return list of sales
     */
    public List<Sale> toSales(World world) {
        return saleView.stream().map(view -> view.toSale(world)).toList();
    }
}
