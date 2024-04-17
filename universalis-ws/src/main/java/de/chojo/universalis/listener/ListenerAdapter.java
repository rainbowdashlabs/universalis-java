/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.listener;

import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;

/**
 * Class implementing the {@link EventListener} for easier usage.
 */
public class ListenerAdapter implements EventListener {
    @Override
    public void onListingAdd(ListingAddEvent event) {

    }

    @Override
    public void onListingRemove(ListingRemoveEvent event) {

    }

    @Override
    public void onListingUpdate(ListingUpdateEvent event) {

    }

    @Override
    public void onSalesAdd(SalesAddEvent event) {

    }

    @Override
    public void onSalesRemove(SalesRemoveEvent event) {

    }
}
