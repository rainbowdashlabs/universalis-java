/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

/**
 * Deserializer to deserialize world ids and names.
 */
public class WorldDeserializer extends ValueDeserializer<World> {
    @Override
    public World deserialize(JsonParser p, DeserializationContext ctxt) {
        if (p.isExpectedNumberIntToken()) {
            return Worlds.worldById(p.getValueAsInt());
        }
        return Worlds.worldByName(p.getValueAsString());
    }
}
