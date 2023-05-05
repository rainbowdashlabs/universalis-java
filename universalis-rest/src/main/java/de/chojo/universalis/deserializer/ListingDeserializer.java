/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.views.ListingView;

import java.io.IOException;

/**
 * Deserializer for {@link Listing}
 */
public class ListingDeserializer extends JsonDeserializer<Listing> {
    @Override
    public Listing deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return ctxt.readValue(p, ListingView.class).toListing();
    }
}
