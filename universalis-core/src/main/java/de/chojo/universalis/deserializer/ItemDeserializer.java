/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.provider.NameSupplier;

import java.io.IOException;

/**
 * Deserializer for {@link Item}
 */
public class ItemDeserializer extends JsonDeserializer<Item> {
    private final NameSupplier itemNameSupplier;

    /**
     * Create a new item deserializer
     *
     * @param itemNameSupplier item name supplier
     */
    public ItemDeserializer(NameSupplier itemNameSupplier) {
        this.itemNameSupplier = itemNameSupplier;
    }

    @Override
    public Item deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Item.build(itemNameSupplier, p.getValueAsInt());
    }
}
