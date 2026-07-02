/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.response.MarketableResponse;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.List;

/**
 * Deserializer for {@link MarketableResponse}
 */
public class MarketableResponseDeserializer extends ValueDeserializer<MarketableResponse> {
    @Override
    public MarketableResponse deserialize(JsonParser p, DeserializationContext ctxt) {
        List<Item> items = ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, Item.class));
        return new MarketableResponse(items);
    }
}
