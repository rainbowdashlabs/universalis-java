/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.response.MarketableResponse;

import java.io.IOException;
import java.util.List;

/**
 * Deserializer for {@link MarketableResponse}
 */
public class MarketableResponseDeserializer extends JsonDeserializer<MarketableResponse> {
    @Override
    public MarketableResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Item> items = ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, Item.class));
        return new MarketableResponse(items);
    }
}
