/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.rest.response.extra.ContentResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Deserializer for {@link ContentResponse}
 */
public class ContentResponseDeserializer extends JsonDeserializer<ContentResponse> {
    @Override
    public ContentResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, Object> map = ctxt.readValue(p, ctxt.getTypeFactory()
                                                        .constructMapType(HashMap.class, String.class, Object.class));
        return new ContentResponse(map);
    }
}
