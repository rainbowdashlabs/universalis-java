/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo;

import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.listener.ListenerAdapter;
import de.chojo.universalis.provider.items.Items;
import de.chojo.universalis.websocket.UniversalisWs;
import de.chojo.universalis.websocket.subscriber.Subscriptions;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

public class WebsocketExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        // We create an items instance to map item ids to item object names. You can also use your own name supplier.
        Items items = Items.create();
        // Create a new default websocket
        UniversalisWs.getDefault()
                     // Set the item name supplier
                     .itemNameSupplier(items)
                     // Subscribe to sales add channel of odin
                     .subscribe(Subscriptions.salesAdd().restrict(Worlds.europe().light().odin))
                     // Subscribe to listing add for all worlds on light and the one world on chaos
                     .subscribe(Subscriptions.listingAdd().restrict(Worlds.europe().light())
                                             .restrict(Worlds.europe().chaos().omega))
                     // subcribe to the remove channel on all worlds
                     .subscribe(Subscriptions.listingRemove())
                     // Register listener to handle evens
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

                         @Override
                         public void onListingUpdate(ListingUpdateEvent event) {
                             System.out.println("Listings updated of " + event.item() + " on world " + event.world());
                         }
                     })
                     .build();
    }
}
