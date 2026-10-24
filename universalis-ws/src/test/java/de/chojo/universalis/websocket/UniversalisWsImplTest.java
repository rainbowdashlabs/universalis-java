/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket;

import com.neovisionaries.ws.client.WebSocketFactory;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.websocket.subscriber.Subscriptions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class UniversalisWsImplTest {
    private final UniversalisWsImpl ws = new UniversalisWsImpl(
            new WebSocketFactory(),
            Executors.newSingleThreadExecutor(),
            List.of(),
            List.of(),
            NameSupplier.EMPTY,
            "ws://localhost:1");

    @Test
    void subscriptionsChangeWhileNotConnected() {
        var subscription = Subscriptions.salesAdd();
        assertDoesNotThrow(() -> ws.subscribe(subscription));
        assertDoesNotThrow(() -> ws.unsubscribe(subscription));
    }
}
