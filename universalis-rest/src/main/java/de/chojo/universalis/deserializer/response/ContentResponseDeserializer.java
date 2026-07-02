/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import de.chojo.universalis.rest.response.extra.ContentResponse;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Deserializer for {@link ContentResponse}
 */
public class ContentResponseDeserializer extends ValueDeserializer<ContentResponse> {
    @Override
    public ContentResponse deserialize(JsonParser p, DeserializationContext ctxt) {
        Map<String, Object> map = ctxt.readValue(p, ctxt.getTypeFactory()
                                                        .constructMapType(HashMap.class, String.class, Object.class));
        return new ContentResponse(map);
    }
}
