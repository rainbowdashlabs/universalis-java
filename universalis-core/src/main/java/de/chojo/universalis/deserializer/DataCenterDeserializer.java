/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Worlds;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * Deserializer for data center
 */
public class DataCenterDeserializer extends ValueDeserializer<DataCenter> {
    @Override
    public DataCenter deserialize(JsonParser p, DeserializationContext ctxt) {
        if (p.isExpectedNumberIntToken()) {
            return Worlds.datacenterById(p.getValueAsInt());
        }
        return Worlds.datacenterByName(p.getValueAsString());
    }
}
