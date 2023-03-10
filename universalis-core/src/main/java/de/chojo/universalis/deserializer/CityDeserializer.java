/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.City;

import java.io.IOException;

/**
 * Deserializer for cities from id or names.
 */
public class CityDeserializer extends JsonDeserializer<City> {
    @Override
    public City deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.isExpectedNumberIntToken()) {
            return City.fromId(p.getValueAsInt());
        }
        return City.fromName(p.getValueAsString());
    }
}
