/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import de.chojo.universalis.connection.events.concrete.listing.impl.WsListingAdd;
import de.chojo.universalis.connection.events.concrete.listing.impl.WsListingRemove;
import de.chojo.universalis.connection.events.concrete.listing.impl.WsListingUpdate;
import de.chojo.universalis.connection.events.concrete.sales.impl.WsSalesAdd;
import de.chojo.universalis.connection.events.concrete.sales.impl.WsSalesRemove;
import de.chojo.universalis.events.listings.impl.ListingAdd;
import de.chojo.universalis.events.listings.impl.ListingRemove;
import de.chojo.universalis.events.sales.impl.SalesAdd;
import de.chojo.universalis.events.sales.impl.SalesRemove;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import org.bson.BSONDecoder;
import org.bson.BasicBSONDecoder;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class WebsocketListenerAdapter extends WebSocketAdapter {
    private static final Logger log = getLogger(WebsocketListenerAdapter.class);
    private final BSONDecoder decoder = new BasicBSONDecoder();
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private final ObjectMapper objectMapper = new JsonMapper();

    private final Cache<Integer, WsListingRemove> removedListings = CacheBuilder.newBuilder()
                                                                                .expireAfterWrite(Duration.ofSeconds(10))
                                                                                .build();

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
            case "listings/add" -> {
                WsListingAdd wsAdd = objectMapper.convertValue(map, WsListingAdd.class);
                ListingAdd add = wsAdd.toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onListingAdd(add));
                WsListingRemove removed = removedListings.getIfPresent(wsAdd.worldId());
                if (removed == null) break;
                removedListings.invalidate(wsAdd.worldId());
                WsListingUpdate wsListingUpdate = removed.toUpdate(wsAdd);
                wsListingUpdate.toEvent(itemNameSupplier);
            }
            case "listings/remove" -> {
                WsListingRemove wsRemove = objectMapper.convertValue(map, WsListingRemove.class);
                ListingRemove remove = wsRemove.toEvent(itemNameSupplier);
                listeners.forEach(l -> l.onListingRemove(remove));
                removedListings.put(wsRemove.worldId(), wsRemove);
            }
        }
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }
}
