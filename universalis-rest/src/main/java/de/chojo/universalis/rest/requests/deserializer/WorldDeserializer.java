/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

public class WorldDeserializer extends JsonDeserializer<World> {


    @Override
    public World deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return Worlds.worldById(p.getValueAsInt());
    }
}
