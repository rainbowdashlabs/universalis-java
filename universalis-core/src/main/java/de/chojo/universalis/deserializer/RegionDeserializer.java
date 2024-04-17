/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

/**
 * Deserializer for {@link Region}
 */
public class RegionDeserializer extends JsonDeserializer<Region> {


    @Override
    public Region deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Worlds.regionByName(p.getText());
    }
}
