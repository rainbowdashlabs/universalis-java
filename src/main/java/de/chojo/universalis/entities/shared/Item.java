/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.shared;

import de.chojo.universalis.provider.NameSupplier;

public record Item(int id, Name name) {
    public static Item build(NameSupplier items, int id) {
        return new Item(id, items.fromId(id));
    }
}
