/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.response.MarketableResponse;

import java.io.IOException;
import java.util.List;

public class MarketableResponseDeserializer extends JsonDeserializer<MarketableResponse> {
    @Override
    public MarketableResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<Item> items = ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, Item.class));
        return new MarketableResponse(items);
    }
}
