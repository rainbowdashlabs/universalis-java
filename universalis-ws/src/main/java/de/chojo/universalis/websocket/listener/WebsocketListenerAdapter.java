/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import de.chojo.universalis.websocket.events.EventSupplier;
import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.websocket.events.concrete.listing.impl.WsListingAddEvent;
import de.chojo.universalis.websocket.events.concrete.listing.impl.WsListingRemoveEvent;
import de.chojo.universalis.websocket.events.concrete.sales.impl.WsSalesAddEvent;
import de.chojo.universalis.websocket.events.concrete.sales.impl.WsSalesRemoveEvent;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import org.bson.BSONDecoder;
import org.bson.BasicBSONDecoder;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * The listener to receive and forward the {@link WsEvent}s to the registered {@link EventListener}s.
 */
public class WebsocketListenerAdapter extends WebSocketAdapter implements EventListener {
    private static final Logger log = getLogger(WebsocketListenerAdapter.class);
    private final BSONDecoder decoder = new BasicBSONDecoder();
    private final List<EventListener> listeners;
    private final NameSupplier itemNameSupplier;
    private final ObjectMapper objectMapper = new JsonMapper();
    private final Cache<Integer, WsListingRemoveEvent> removedListings = CacheBuilder.newBuilder()
                                                                                     .expireAfterWrite(Duration.ofSeconds(10))
                                                                                     .build();

    public WebsocketListenerAdapter(List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.listeners = listeners;
        this.itemNameSupplier = itemNameSupplier;
    }

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
        Map<?, ?> map = decoder.readObject(binary).toMap();
        String event = (String) map.remove("event");
        log.trace("Received event {}", event);
        log.trace("{}", objectMapper.writeValueAsString(map));
        switch (event) {
            case "sales/add" -> {
                onSalesAdd(mapToEvent(map, WsSalesAddEvent.class));
            }
            case "sales/remove" -> {
                onSalesRemove(mapToEvent(map, WsSalesRemoveEvent.class));
            }
            case "listings/add" -> {
                WsListingAddEvent wsAdd = map(map, WsListingAddEvent.class);
                onListingAdd(wsAdd.toEvent(itemNameSupplier));
                handleAdd(wsAdd);
            }
            case "listings/remove" -> {
                WsListingRemoveEvent wsRemove = map(map, WsListingRemoveEvent.class);
                onListingRemove(wsRemove.toEvent(itemNameSupplier));
                removedListings.put(wsRemove.worldId(), wsRemove);
            }
        }
    }

    private void handleAdd(WsListingAddEvent wsAdd) {
        WsListingRemoveEvent removed = removedListings.getIfPresent(wsAdd.worldId());
        if (removed == null) return;
        removedListings.invalidate(wsAdd.worldId());
        onListingUpdate(removed.toUpdate(wsAdd).toEvent(itemNameSupplier));
    }

    private <V extends Event, T extends EventSupplier<V>> V mapToEvent(Map<?, ?> map, Class<T> clazz) {
        return map(map, clazz).toEvent(itemNameSupplier);
    }

    private <V extends Event, T extends EventSupplier<V>> T map(Map<?, ?> map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    @Override
    public void onError(WebSocket websocket, WebSocketException cause) throws Exception {
        log.error("Error in websocket", cause);
        log.error("Reason: {}", cause.getError());
    }

    @Override
    public void onListingAdd(ListingAddEvent event) {
        listeners.forEach(listenet -> listenet.onListingAdd(event));
    }

    @Override
    public void onListingRemove(ListingRemoveEvent event) {
        listeners.forEach(listenet -> listenet.onListingRemove(event));
    }

    @Override
    public void onListingUpdate(ListingUpdateEvent event) {
        listeners.forEach(listenet -> listenet.onListingUpdate(event));
    }

    @Override
    public void onSalesAdd(SalesAddEvent event) {
        listeners.forEach(listenet -> listenet.onSalesAdd(event));
    }

    @Override
    public void onSalesRemove(SalesRemoveEvent event) {
        listeners.forEach(listenet -> listenet.onSalesRemove(event));
    }
}
