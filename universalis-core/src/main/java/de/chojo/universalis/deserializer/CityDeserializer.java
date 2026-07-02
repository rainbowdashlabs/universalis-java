/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.entities.City;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * Deserializer for cities from id or names.
 */
public class CityDeserializer extends ValueDeserializer<City> {
    @Override
    public City deserialize(JsonParser p, DeserializationContext ctxt) {
        if (p.isExpectedNumberIntToken()) {
            return City.fromId(p.getValueAsInt());
        }
        return City.fromName(p.getValueAsString());
    }
}
