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
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.views.ListingView;

import java.io.IOException;

public class ListingDeserializer extends JsonDeserializer<Listing> {
    @Override
    public Listing deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return ctxt.readValue(p, ListingView.class).toListing();
    }
}
