/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.worlds.Worlds;
import org.slf4j.Logger;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.KeyDeserializer;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Deserializer for worlds as a key of a map
 */
public class DataCenterKeyDeserializer extends KeyDeserializer {
    private static final Logger log = getLogger(DataCenterKeyDeserializer.class);

    @Override
    public Object deserializeKey(String key, DeserializationContext ctxt) {
        try {
            return Worlds.datacenterById(Integer.parseInt(key));
        } catch (NumberFormatException e) {
            log.trace("Datacenter key '{}' is not an integer, resolving by name", key, e);
            return Worlds.datacenterByName(key);
        }
    }
}
