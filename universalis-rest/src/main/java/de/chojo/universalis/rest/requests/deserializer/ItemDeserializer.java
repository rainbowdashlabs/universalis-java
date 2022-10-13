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
import de.chojo.universalis.provider.NameSupplier;

import java.io.IOException;

public class ItemDeserializer extends JsonDeserializer<Item> {
    private final NameSupplier itemSupplier;

    public ItemDeserializer(NameSupplier itemSupplier) {
        this.itemSupplier = itemSupplier;
    }

    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Item.build(itemSupplier, p.getValueAsInt());
    }
}
