/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.provider.NameSupplier;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * Deserializer for {@link Item}
 */
public class ItemDeserializer extends ValueDeserializer<Item> {
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
    public Item deserialize(JsonParser p, DeserializationContext ctxt) {
        return Item.build(itemNameSupplier, p.getValueAsInt());
    }
}
