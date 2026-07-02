/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.response.DataCentersResponse;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.List;

/**
 * Deserializer for {@link DataCentersResponse}
 */
public class DataCenterResponseDeserializer extends ValueDeserializer<DataCentersResponse> {
    @Override
    public DataCentersResponse deserialize(JsonParser p, DeserializationContext ctxt) {
        List<DataCenter> contentType = ctxt.readValue(p, ctxt.getTypeFactory()
                                                             .constructCollectionType(List.class, DataCenter.class));
        return new DataCentersResponse(contentType);
    }
}
