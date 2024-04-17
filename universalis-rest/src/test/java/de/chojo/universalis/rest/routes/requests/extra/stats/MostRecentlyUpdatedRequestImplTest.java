/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.routes.api.extra.stats.MostRecentlyUpdatedRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static de.chojo.universalis.rest.ClientWrapper.client;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MostRecentlyUpdatedRequestImplTest {


    @ParameterizedTest
    @MethodSource("inputs")
    public void test(MostRecentlyUpdatedRequest request) {
        var complete = request.complete();
        assertFalse(complete.items().isEmpty());
    }

    public static Stream<MostRecentlyUpdatedRequest> inputs() {
        return Stream.of(worldReq(), dcReq());
    }

    private static MostRecentlyUpdatedRequest worldReq() {
        return client().extra().stats().mostRecentlyUpdated()
                .world(Worlds.europe().light().odin);
    }

    private static MostRecentlyUpdatedRequest dcReq() {
        return client().extra().stats().mostRecentlyUpdated()
                .dataCenter(Worlds.europe().light());
    }
}
