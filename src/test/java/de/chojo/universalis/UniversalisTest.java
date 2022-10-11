/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import com.neovisionaries.ws.client.WebSocketException;
import de.chojo.universalis.subscriber.Subscriptions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class UniversalisTest {

    @Test
    public void testDefault() throws WebSocketException, IOException, InterruptedException {
        Universalis.getDefaultSocket()
                   .subscribe(Subscriptions.listingAdd())
                   .subscribe(Subscriptions.listingRemove())
                   .build();
    }

}
