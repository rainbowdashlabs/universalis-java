/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

/**
 * Deserializer for worlds as a key of a map
 */
public class WorldKeyDeserializer extends KeyDeserializer {
    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException {
        try {
            return Worlds.worldById(Integer.parseInt(key));
        } catch (NumberFormatException e) {
            return Worlds.worldByName(key);
        }
    }
}
