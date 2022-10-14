/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.response.DataCentersResponse;

import java.io.IOException;
import java.util.List;

public class DataCenterResponseDeserializer extends JsonDeserializer<DataCentersResponse> {
    @Override
    public DataCentersResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        List<DataCenter> contentType = ctxt.readValue(p, ctxt.getTypeFactory().constructCollectionType(List.class, DataCenter.class));
        return new DataCentersResponse(contentType);
    }
}
