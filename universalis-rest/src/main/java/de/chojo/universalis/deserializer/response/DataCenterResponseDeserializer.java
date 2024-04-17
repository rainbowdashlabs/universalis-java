/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.response.DataCentersResponse;

import java.io.IOException;
import java.util.List;

/**
 * Deserializer for {@link DataCentersResponse}
 */
public class DataCenterResponseDeserializer extends JsonDeserializer<DataCentersResponse> {
    @Override
    public DataCentersResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<DataCenter> contentType = ctxt.readValue(p, ctxt.getTypeFactory()
                                                             .constructCollectionType(List.class, DataCenter.class));
        return new DataCentersResponse(contentType);
    }
}
