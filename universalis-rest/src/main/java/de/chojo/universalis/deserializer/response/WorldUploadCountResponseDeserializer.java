/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.views.WorldUploadCountView;
import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import de.chojo.universalis.worlds.World;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Deserializer for {@link WorldUploadCountResponse}
 */
public class WorldUploadCountResponseDeserializer extends JsonDeserializer<WorldUploadCountResponse> {
    @Override
    public WorldUploadCountResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<World, WorldUploadCountView> map = ctxt.readValue(p, ctxt.getTypeFactory()
                                                                     .constructMapType(HashMap.class, World.class, WorldUploadCountView.class));
        return new WorldUploadCountResponse(map);
    }
}
