/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.Name;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NameSupplierTest {
    private final NameSupplier supplier = new NameSupplier() {
        @Override
        public Map<Integer, Name> ids() {
            return Map.of(5057, new Name("Iron Ore", "Eisenerz", "Minerai de fer", "鉄鉱"));
        }

        @Override
        public Map<String, Integer> en() {
            return Map.of("iron ore", 5057);
        }

        @Override
        public Map<String, Integer> de() {
            return Map.of();
        }

        @Override
        public Map<String, Integer> fr() {
            return Map.of();
        }

        @Override
        public Map<String, Integer> jp() {
            return Map.of();
        }
    };

    @Test
    void knownIdHasItsName() {
        assertEquals("Eisenerz", supplier.fromId(5057).german());
    }

    @Test
    void unknownIdHasAnEmptyName() {
        assertEquals(Name.empty(), supplier.fromId(1));
    }
}
