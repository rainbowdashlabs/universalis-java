/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.views.ListingView;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * Deserializer for {@link Listing}
 */
public class ListingDeserializer extends ValueDeserializer<Listing> {
    @Override
    public Listing deserialize(JsonParser p, DeserializationContext ctxt) {
        return ctxt.readValue(p, ListingView.class).toListing();
    }
}
