/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

public class RestExample {
    public static void main(String[] args) {
        // Create a rest client with default settings.
        UniversalisRest rest = UniversalisRest.builder().build();

        // retrieve valid worlds synchronours
        WorldsResponse worlds = rest.worlds().complete();
        for (World world : worlds) {
            System.out.printf("World %s exists%n", world.name());
        }
        // create a market board request.
        rest.marketBoard()
            // Restrict prices to the european datacenters
            .region(Worlds.europe())
            // Get data for only one item
            .itemsIds(36113)
            // only retrieve high quality prices
            .highQuality()
            // exclude taxes
            .noGst()
            // send the request async
            .queue()
            // handle the result
            .whenComplete((res, err) -> {
                System.out.println("Min hq price is" + res.minPrice().highQuality());
            });
    }
}
