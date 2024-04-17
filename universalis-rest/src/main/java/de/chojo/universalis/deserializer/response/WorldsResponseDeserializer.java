/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Deserializer for {@link WorldsResponse}
 */
public class WorldsResponseDeserializer extends JsonDeserializer<WorldsResponse> {
    @Override
    public WorldsResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<World> worlds = ctxt.readValue(p,
                ctxt.getTypeFactory().constructCollectionType(List.class, World.class));
        worlds = worlds.stream().filter(Objects::nonNull).filter(w -> w.id() != 0).toList();
        List<de.chojo.universalis.worlds.World> iWorlds = worlds.stream().map(Worlds::toWorld).toList();
        return new WorldsResponse(iWorlds);
    }
}
