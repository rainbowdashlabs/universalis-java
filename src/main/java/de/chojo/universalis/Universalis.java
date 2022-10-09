/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import de.chojo.universalis.connection.builder.UniversalisBuilder;
import de.chojo.universalis.connection.builder.WebsocketFactoryBuilder;

public class Universalis {
    public static WebsocketFactoryBuilder wsBuilder() {
        return new WebsocketFactoryBuilder();
    }

    public static UniversalisBuilder getDefaultWs() {
        return new WebsocketFactoryBuilder().setConnectionTimeout(10000).build();
    }
}
