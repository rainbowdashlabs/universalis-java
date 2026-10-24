/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import org.junit.jupiter.api.Test;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.json.JsonMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Checks the world list against responses of {@code /worlds} and {@code /data-centers} recorded from Universalis.
 * Universalis lists every Japanese world under Elemental, so the data center of those worlds is not compared.
 */
class WorldsUpstreamTest {
    private static final JsonMapper MAPPER = JsonMapper.builder().build();

    @Test
    void everyUpstreamWorldIsKnownWithItsName() throws IOException {
        for (JsonNode world : read("worlds.json")) {
            World known = Worlds.worldById(world.get("id").asInt());
            assertEquals(world.get("name").asString(), known.name(), "world " + world.get("id"));
            assertNotNull(known.dataCenter(), "data center of " + known);
        }
    }

    @Test
    void everyKnownWorldExistsUpstream() throws IOException {
        Set<Integer> upstream = new HashSet<>();
        for (JsonNode world : read("worlds.json")) {
            upstream.add(world.get("id").asInt());
        }
        for (Region region : Worlds.regions()) {
            for (World world : region.worlds()) {
                assertTrue(upstream.contains(world.id()), world + " no longer exists upstream");
            }
        }
    }

    @Test
    void dataCentersAndRegionsMatchUpstreamNames() throws IOException {
        for (JsonNode dataCenter : read("data-centers.json")) {
            String region = dataCenter.get("region").asString();
            assertNotNull(Worlds.regionByName(region), "region " + region);
            DataCenter known = Worlds.datacenterByName(dataCenter.get("name").asString());
            assertNotNull(known, "data center " + dataCenter.get("name"));
            assertEquals(region, known.region().name());
            if ("Japan".equals(region)) continue;
            for (JsonNode id : dataCenter.get("worlds")) {
                assertEquals(known, Worlds.worldById(id.asInt()).dataCenter(), "data center of world " + id);
            }
        }
    }

    private static JsonNode read(String resource) throws IOException {
        try (InputStream in = WorldsUpstreamTest.class.getClassLoader().getResourceAsStream(resource)) {
            return MAPPER.readTree(in);
        }
    }
}
