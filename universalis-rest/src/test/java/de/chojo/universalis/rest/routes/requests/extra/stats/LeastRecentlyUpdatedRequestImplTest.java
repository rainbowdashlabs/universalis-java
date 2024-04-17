/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests.extra.stats;

import de.chojo.universalis.rest.response.extra.stats.LeastRecentlyUpdatedResponse;
import de.chojo.universalis.rest.routes.api.extra.stats.LeastRecentlyUpdatedRequest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static de.chojo.universalis.rest.ClientWrapper.client;

class LeastRecentlyUpdatedRequestImplTest {


    @ParameterizedTest
    @MethodSource("inputs")
    public void test(LeastRecentlyUpdatedRequest request) {
        LeastRecentlyUpdatedResponse complete = request.complete();
        Assertions.assertFalse(complete.items().isEmpty());
    }

    public static Stream<LeastRecentlyUpdatedRequest> inputs() {
        return Stream.of(worldReq(), dcReq());
    }

    private static LeastRecentlyUpdatedRequest worldReq() {
        return client().extra().stats().leastRecentlyUpdated()
                .world(Worlds.europe().light().odin);
    }

    private static LeastRecentlyUpdatedRequest dcReq() {
        return client().extra().stats().leastRecentlyUpdated()
                .dataCenter(Worlds.europe().light());
    }
}
