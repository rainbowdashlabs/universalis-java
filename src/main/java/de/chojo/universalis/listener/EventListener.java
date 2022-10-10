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

public interface EventListener {
    void onListingAdd(ListingAdd event);

    void onListingRemove(ListingRemove event);

    void onSalesAdd(SalesAdd event);

    void onSalesRemove(SalesRemove event);
}
