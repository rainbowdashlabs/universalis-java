/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.views.SourceUploadCountView;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;

import java.io.IOException;
import java.util.List;

/**
 * Deserializer for {@link UploaderUploadCountResponse}
 */
public class UploaderUploadCountsResponseDeserializer extends JsonDeserializer<UploaderUploadCountResponse> {
    @Override
    public UploaderUploadCountResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<SourceUploadCountView> counts = ctxt.readValue(p, ctxt.getTypeFactory()
                                                                   .constructCollectionType(List.class, SourceUploadCountView.class));
        return new UploaderUploadCountResponse(counts);
    }
}
