/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo;

import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.listener.ListenerAdapter;
import de.chojo.universalis.subscriber.Subscriptions;
import de.chojo.universalis.websocket.UniversalisWs;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UniversalisWs.getDefault()
                     .subscribe(Subscriptions.salesAdd().forWorld(Worlds.europe().light().ODIN))
                     .subscribe(Subscriptions.listingAdd())
                     .subscribe(Subscriptions.salesRemove())
                     .subscribe(Subscriptions.listingRemove())
                     .registerListener(new ListenerAdapter() {
                         @Override
                         public void onListingAdd(ListingAddEvent event) {
                             System.out.println("New listings of " + event.item() + " on world " + event.world());
                             for (var listing : event.listings()) {
                                 System.out.println(listing);
                             }
                         }

                         @Override
                         public void onSalesAdd(SalesAddEvent event) {
                             System.out.println("New sales of " + event.item() + " on world " + event.world());
                             for (var e : event.sales()) {
                                 System.out.println(e);
                             }
                         }
                     })
                     .build();
    }
}
