/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.listener;

import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.listener.ListenerAdapter;
import de.chojo.universalis.provider.NameSupplier;
import org.bson.BasicBSONEncoder;
import org.bson.BasicBSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebsocketListenerAdapterTest {
    private final List<ListingUpdateEvent> updates = new ArrayList<>();
    private final WebsocketListenerAdapter adapter = new WebsocketListenerAdapter(
            List.of(new ListenerAdapter() {
                @Override
                public void onListingUpdate(ListingUpdateEvent event) {
                    updates.add(event);
                }
            }),
            NameSupplier.EMPTY);

    @Test
    void pairsRemoveAndAddOfTheSameItem() throws Exception {
        adapter.onBinaryMessage(null, event("listings/remove", 5057, 66, 100));
        adapter.onBinaryMessage(null, event("listings/add", 5057, 66, 90));

        assertEquals(1, updates.size());
        assertEquals(5057, updates.getFirst().item().id());
        assertEquals(90, updates.getFirst().added().getFirst().price().pricePerUnit());
        assertEquals(100, updates.getFirst().removed().getFirst().price().pricePerUnit());
    }

    @Test
    void keepsDifferentItemsOnTheSameWorldApart() throws Exception {
        adapter.onBinaryMessage(null, event("listings/remove", 5057, 66, 100));
        adapter.onBinaryMessage(null, event("listings/add", 5058, 66, 90));

        assertEquals(0, updates.size());
    }

    private static byte[] event(String event, int item, int world, int price) {
        BasicBSONObject listing = new BasicBSONObject()
                .append("lastReviewTime", 1700000000L)
                .append("pricePerUnit", price)
                .append("quantity", 1)
                .append("stainID", 0)
                .append("worldName", "Odin")
                .append("worldID", world)
                .append("creatorName", "")
                .append("creatorID", null)
                .append("hq", false)
                .append("isCrafted", false)
                .append("listingID", "listing-" + price)
                .append("materia", List.of())
                .append("onMannequin", false)
                .append("retainerCity", 1)
                .append("retainerID", "retainer")
                .append("retainerName", "Retainer")
                .append("sellerID", "seller")
                .append("total", price)
                .append("tax", 5);
        BasicBSONObject payload = new BasicBSONObject()
                .append("event", event)
                .append("item", item)
                .append("world", world)
                .append("listings", List.of(listing));
        return new BasicBSONEncoder().encode(payload);
    }
}
