/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.deser.jdk.StringDeserializer;

/**
 * Serializer to change empty string to null values.
 */
public class EmptyStringDeserializer extends ValueDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) {
        String deserialize = StringDeserializer.instance.deserialize(p, ctxt);
        if (deserialize == null || deserialize.isBlank()) {
            return null;
        }
        return deserialize;
    }
}
