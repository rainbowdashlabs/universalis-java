/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.Worlds;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

/**
 * Deserializer for {@link Region}
 */
public class RegionDeserializer extends ValueDeserializer<Region> {


    @Override
    public Region deserialize(JsonParser p, DeserializationContext ctxt) {
        return Worlds.regionByName(p.getString());
    }
}
