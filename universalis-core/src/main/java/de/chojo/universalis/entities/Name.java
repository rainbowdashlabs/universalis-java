/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Name of an entity
 *
 * @param english  english name
 * @param german   german name
 * @param french   french name
 * @param japanese japanese name
 */
public record Name(@JsonProperty("en") String english,
                   @JsonProperty("de") String german,
                   @JsonProperty("fr") String french,
                   @JsonProperty("ja") String japanese) {
    private static final Name EMPTY = new Name("", "", "", "");

    /**
     * Constant empty name object
     *
     * @return empty instance
     */
    public static Name empty() {
        return EMPTY;
    }

    public String get(Language language) {
        return switch (language) {
            case ENGLISH -> english;
            case GERMAN -> german;
            case FRENCH -> french;
            case JAPANESE -> japanese;
        };
    }
}
