/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import de.chojo.universalis.entities.aggregated.AggregatedItem;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.rest.requests.Mapper;
import de.chojo.universalis.rest.response.AggregatedResponse;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Test;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Maps a response of {@code /aggregated/Light/5057,5058,1} recorded from Universalis.
 */
class AggregatedFixtureTest {
    private final ObjectMapper mapper = Mapper.create(NameSupplier.EMPTY);

    @Test
    void readsRecordedResponse() throws IOException {
        AggregatedResponse response;
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("aggregated-light.json")) {
            response = mapper.readValue(in, AggregatedResponse.class);
        }

        assertEquals(2, response.items().size());
        assertEquals(1, response.failedItems().getFirst().id());

        AggregatedItem ore = response.items().getFirst();
        assertEquals(5057, ore.item().id());
        assertNull(ore.normalQuality().minListing().world());
        assertEquals(68, ore.normalQuality().minListing().dataCenter().price());
        assertEquals(
                Worlds.europe().light().twintania,
                ore.normalQuality().minListing().dataCenter().world());
        assertEquals(
                Instant.ofEpochMilli(1792797377000L),
                ore.normalQuality().recentPurchase().dataCenter().timestamp());
        assertEquals(
                697.5667926013108,
                ore.normalQuality().dailySaleVelocity().dataCenter().quantity());
        assertEquals(
                366.1598108747045,
                ore.normalQuality().averageSalePrice().dataCenter().price());
        assertEquals(
                Worlds.europe().light().twintania,
                ore.worldUploadTimes().getFirst().world());
    }
}
