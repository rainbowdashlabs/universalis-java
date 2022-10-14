/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

/**
 * Deserializer to deserialize world ids and names.
 */
public class WorldDeserializer extends JsonDeserializer<World> {
    @Override
    public World deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.isExpectedNumberIntToken()) {
            return Worlds.worldById(p.getValueAsInt());
        }
        return Worlds.worldByName(p.getValueAsString());
    }
}
