/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Regression coverage for the world/datacenter registry.
 */
class WorldsTest {

    @Test
    void allDataCenterIdsAreDistinct() {
        Set<Integer> seen = new HashSet<>();
        for (Region region : Worlds.regions()) {
            for (DataCenter dc : region.datacenters()) {
                assertTrue(seen.add(dc.id()),
                        "duplicate datacenter id " + dc.id() + " on " + dc.name());
            }
        }
    }

    @Test
    void datacenterByIdRoundTripsAllKnownDatacenters() {
        for (Region region : Worlds.regions()) {
            for (DataCenter dc : region.datacenters()) {
                DataCenter looked = Worlds.datacenterById(dc.id());
                assertNotNull(looked, "datacenter " + dc.name() + " not found by id " + dc.id());
                assertEquals(dc.name(), looked.name(),
                        "datacenter id " + dc.id() + " resolved to wrong datacenter");
            }
        }
    }

    @Test
    void datacenterByIdReturnsNullForUnknown() {
        assertNull(Worlds.datacenterById(Integer.MIN_VALUE));
    }

    @Test
    void worldOfEqualsAndHashCodeAreConsistent() {
        World a = World.of("Odin", 66, null);
        World b = World.of("Odin", 66, null);
        World c = World.of("Odin", 67, null);
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a, c);
    }

    @Test
    void worldByIdIsThreadSafe() throws InterruptedException, ExecutionException {
        int threads = 16;
        int iterations = 500;
        var exec = Executors.newFixedThreadPool(threads);
        try {
            List<Future<?>> futures = new java.util.ArrayList<>();
            for (int t = 0; t < threads; t++) {
                futures.add(exec.submit(() -> {
                    for (int i = 0; i < iterations; i++) {
                        assertNotNull(Worlds.worldById(66));
                        assertNotNull(Worlds.worldById(100_000 + (i % 32)));
                    }
                }));
            }
            for (Future<?> f : futures) {
                f.get();
            }
        } finally {
            exec.shutdownNow();
        }
    }
}
