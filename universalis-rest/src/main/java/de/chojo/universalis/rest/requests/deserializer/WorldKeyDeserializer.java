/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

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
