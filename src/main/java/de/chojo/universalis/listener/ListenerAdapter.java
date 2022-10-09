/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.listener;

import de.chojo.universalis.events.listings.ListingAdd;
import de.chojo.universalis.events.listings.ListingRemove;
import de.chojo.universalis.events.sales.SalesAdd;
import de.chojo.universalis.events.sales.SalesRemove;

public class ListenerAdapter implements EventListener{
    @Override
    public void onListingAdd(ListingAdd event) {

    }

    @Override
    public void onListingRemove(ListingRemove event) {

    }

    @Override
    public void onSalesAdd(SalesAdd event) {

    }

    @Override
    public void onSalesRemove(SalesRemove event) {

    }
}
