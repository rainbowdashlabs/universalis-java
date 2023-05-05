/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.Name;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Interface to supply entity names based on an id
 */
public interface NameSupplier {
    NameSupplier EMPTY = new NameSupplier() {
        @Override
        public Name fromId(int id) {
            return new Name("", "", "", "");
        }

        @Override
        public Optional<Integer> fromName(String name) {
            return Optional.empty();
        }

        @Override
        public Map<Integer, Name> ids() {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, Integer> en() {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, Integer> de() {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, Integer> fr() {
            return Collections.emptyMap();
        }

        @Override
        public Map<String, Integer> jp() {
            return Collections.emptyMap();
        }
    };

    /**
     * Returns the name based on an id
     *
     * @param id id
     * @return name
     */
    Name fromId(int id);

    Optional<Integer> fromName(String name);

    Map<Integer, Name> ids();

    Map<String, Integer> en();

    Map<String, Integer> de();

    Map<String, Integer> fr();

    Map<String, Integer> jp();
}
