/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.rest.response.extra.ContentResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ContentResponseDeserializer extends JsonDeserializer<ContentResponse> {
    @Override
    public ContentResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<String, Object> map = ctxt.readValue(p, ctxt.getTypeFactory()
                                                        .constructMapType(HashMap.class, String.class, Object.class));
        return new ContentResponse(map);
    }
}
