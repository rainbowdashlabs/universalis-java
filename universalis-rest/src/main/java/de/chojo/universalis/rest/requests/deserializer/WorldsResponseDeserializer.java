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
import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.entities.World;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.response.WorldsResponse;

import java.io.IOException;
import java.util.List;

public class WorldsResponseDeserializer extends JsonDeserializer<WorldsResponse> {
    @Override
    public WorldsResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        List<World> contentType = ctxt.readValue(p,
                ctxt.getTypeFactory().constructCollectionType(List.class, World.class));
        //TODO: investigate worlds with 0 ids.
        contentType = contentType.stream().filter(w -> w.id() != 0).toList();
        return new WorldsResponse(contentType);
    }
}
