/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import de.chojo.universalis.entities.views.SourceUploadCountView;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;

import java.util.List;

/**
 * Deserializer for {@link UploaderUploadCountResponse}
 */
public class UploaderUploadCountsResponseDeserializer extends ValueDeserializer<UploaderUploadCountResponse> {
    @Override
    public UploaderUploadCountResponse deserialize(JsonParser p, DeserializationContext ctxt) {
        List<SourceUploadCountView> counts = ctxt.readValue(p, ctxt.getTypeFactory()
                                                                   .constructCollectionType(List.class, SourceUploadCountView.class));
        return new UploaderUploadCountResponse(counts);
    }
}
