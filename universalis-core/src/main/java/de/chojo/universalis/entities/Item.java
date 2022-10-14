/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.provider.NameSupplier;

/**
 * Represents an item. Includes localised names.
 *
 * @param id   id
 * @param name name
 */
public record Item(int id, Name name) {
    /**
     * Creates an item based on an id and retrieves the names vie the supplier
     *
     * @param nameSupplier name supplier
     * @param id           item id
     * @return item
     */
    public static Item build(NameSupplier nameSupplier, int id) {
        return new Item(id, nameSupplier.fromId(id));
    }

    /**
     * Create a new item by id with an empty name
     *
     * @param id id
     * @return new item instance
     */
    public static Item ofId(int id) {
        return new Item(id, Name.empty());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;

        return id == item.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
