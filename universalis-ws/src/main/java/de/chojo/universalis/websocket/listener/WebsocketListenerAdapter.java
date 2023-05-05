/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.websocket.listener;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import de.chojo.universalis.deserializer.CityDeserializer;
import de.chojo.universalis.deserializer.ItemDeserializer;
import de.chojo.universalis.deserializer.WorldDeserializer;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.events.Event;
import de.chojo.universalis.events.listings.impl.ListingAddEvent;
import de.chojo.universalis.events.listings.impl.ListingRemoveEvent;
import de.chojo.universalis.events.listings.impl.ListingUpdateEvent;
import de.chojo.universalis.events.sales.impl.SalesAddEvent;
import de.chojo.universalis.events.sales.impl.SalesRemoveEvent;
import de.chojo.universalis.listener.EventListener;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.websocket.events.EventSupplier;
import de.chojo.universalis.websocket.events.WsEvent;
import de.chojo.universalis.websocket.events.concrete.listing.impl.WsListingAddEvent;
import de.chojo.universalis.websocket.events.concrete.listing.impl.WsListingRemoveEvent;
import de.chojo.universalis.websocket.events.concrete.sales.impl.WsSalesAddEvent;
import de.chojo.universalis.websocket.events.concrete.sales.impl.WsSalesRemoveEvent;
import de.chojo.universalis.worlds.World;
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
    private final ObjectMapper objectMapper;
    private final Cache<World, WsListingRemoveEvent> removedListings = CacheBuilder.newBuilder()
                                                                                   .expireAfterWrite(Duration.ofSeconds(10))
                                                                                   .build();

    /**
     * Creates a new websocket listener adapter
     *
     * @param listeners        listeners to register
     * @param itemNameSupplier item name supplier
     */
    public WebsocketListenerAdapter(List<EventListener> listeners, NameSupplier itemNameSupplier) {
        this.listeners = listeners;
        SimpleModule module = new SimpleModule();
        module.addDeserializer(World.class, new WorldDeserializer())
              .addDeserializer(Item.class, new ItemDeserializer(itemNameSupplier))
//              .addDeserializer(Instant.class, new SecondsDateTimeConverter())
              .addDeserializer(City.class, new CityDeserializer());
        objectMapper = new JsonMapper().registerModule(module)
                                       .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
                onListingAdd(wsAdd.toEvent());
                handleAdd(wsAdd);
            }
            case "listings/remove" -> {
                WsListingRemoveEvent wsRemove = map(map, WsListingRemoveEvent.class);
                onListingRemove(wsRemove.toEvent());
                removedListings.put(wsRemove.world(), wsRemove);
            }
        }
    }

    private void handleAdd(WsListingAddEvent wsAdd) {
        WsListingRemoveEvent removed = removedListings.getIfPresent(wsAdd.world());
        if (removed == null) return;
        removedListings.invalidate(wsAdd.world());
        onListingUpdate(removed.toUpdate(wsAdd).toEvent());
    }

    private <V extends Event, T extends EventSupplier<V>> V mapToEvent(Map<?, ?> map, Class<T> clazz) {
        return map(map, clazz).toEvent();
    }

    private <V extends Event, T extends EventSupplier<V>> T map(Map<?, ?> map, Class<T> clazz) {
        try {

            return objectMapper.convertValue(map, clazz);
        } catch (IllegalArgumentException e) {
            log.error("Failed to map event", e);
            throw new RuntimeException("", e);
        }
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
