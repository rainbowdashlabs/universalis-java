/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import de.chojo.universalis.connection.events.concrete.listing.impl.WsListingAdd;
import de.chojo.universalis.connection.events.concrete.listing.impl.WsListingRemove;
import de.chojo.universalis.connection.events.concrete.sales.impl.WsSalesAdd;
import de.chojo.universalis.connection.events.concrete.sales.impl.WsSalesRemove;
import de.chojo.universalis.events.listings.ListingAdd;
import de.chojo.universalis.events.listings.ListingRemove;
import de.chojo.universalis.events.sales.SalesAdd;
import de.chojo.universalis.events.sales.SalesRemove;
import de.chojo.universalis.items.NameSupplier;
import de.chojo.universalis.listener.EventListener;
import org.bson.BSONDecoder;
import org.bson.BasicBSONDecoder;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class WebsocketListenerAdapter extends WebSocketAdapter {
    private static final Logger log = getLogger(WebsocketListenerAdapter.class);
    private final BSONDecoder decoder = new BasicBSONDecoder();
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private final ObjectMapper objectMapper = new JsonMapper();

    public WebsocketListenerAdapter(List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
    }

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
        Map map = decoder.readObject(binary).toMap();
        String event = (String) map.remove("event");
        log.trace("Received event {}", event);
        log.trace("{}", objectMapper.writeValueAsString(map));
        switch (event) {
            case "sales/add" -> {
                SalesAdd add = objectMapper.convertValue(map, WsSalesAdd.class).toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onSalesAdd(add));
            }
            case "sales/remove" -> {
                SalesRemove remove = objectMapper.convertValue(map, WsSalesRemove.class).toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onSalesRemove(remove));
            }
            case "listing/add" -> {
                ListingAdd add = objectMapper.convertValue(map, WsListingAdd.class).toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onListingAdd(add));
            }
            case "listing/remove" -> {
                ListingRemove remove = objectMapper.convertValue(map, WsListingRemove.class).toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onListingRemove(remove));
            }
        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }
}
