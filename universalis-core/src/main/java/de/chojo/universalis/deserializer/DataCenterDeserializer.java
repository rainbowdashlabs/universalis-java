/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.worlds.DataCenter;
import de.chojo.universalis.worlds.Worlds;

import java.io.IOException;

/**
 * Deserializer for data center
 */
public class DataCenterDeserializer extends JsonDeserializer<DataCenter> {
    @Override
    public DataCenter deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Worlds.datacenterByName(p.getText());
    }
}
