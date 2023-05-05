/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Name of an entity
 *
 * @param english  englisch name
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
}
