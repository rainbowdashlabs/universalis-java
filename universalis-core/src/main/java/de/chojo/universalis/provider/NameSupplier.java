/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.Language;
import de.chojo.universalis.entities.Name;

import java.util.Collections;
import java.util.Locale;
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

    default Name fromId(int id) {
        return ids().get(id);
    }

    default Optional<Integer> fromName(String name) {
        for (Language lang : Language.values()) {
            Optional<Integer> id = fromName(lang, name);
            if (id.isPresent()) return id;
        }
        return Optional.empty();
    }

    default Optional<Integer> fromName(Language language, String name) {
        return Optional.ofNullable(languageMap(language).get(name.toLowerCase(Locale.ROOT)));
    }

    Map<Integer, Name> ids();

    default Map<String, Integer> languageMap(Language language) {
        return switch (language) {
            case ENGLISH -> en();
            case GERMAN -> de();
            case FRENCH -> fr();
            case JAPANESE -> jp();
        };
    }

    Map<String, Integer> en();

    Map<String, Integer> de();

    Map<String, Integer> fr();

    Map<String, Integer> jp();
}
