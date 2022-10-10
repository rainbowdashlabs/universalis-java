/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import de.chojo.universalis.connection.builder.UniversalisWsBuilder;
import de.chojo.universalis.connection.builder.WebsocketFactoryBuilder;

/**
 * Main class to create instances for accessing universalis
 */
public class Universalis {
    /**
     * Get a websocket builder. Use this if you want more control about the socket itself.
     * <p>
     * Use {@link #getDefaultSocket()} if your don't know that this means.
     *
     * @return websocket factory builder
     */
    public static WebsocketFactoryBuilder websocketBuilder() {
        return new WebsocketFactoryBuilder();
    }

    /**
     * Get a universalis builder for websockets.
     *
     * @return universalis websocket builder
     */
    public static UniversalisWsBuilder getDefaultSocket() {
        return new WebsocketFactoryBuilder().setConnectionTimeout(10000).build();
    }
}
