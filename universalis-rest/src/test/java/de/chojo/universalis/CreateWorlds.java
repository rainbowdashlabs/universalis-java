/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.worlds.World;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.StringJoiner;

public class CreateWorlds {

    // Ugly class creating dummy code for the worlds class
    @Test
    @Disabled
    public void createWorlds() {
        UniversalisRest universalisRest = UniversalisRest.defaultApi();

        WorldsResponse worlds = universalisRest.worlds().complete();

        DataCentersResponse dataCenters = universalisRest.dataCenters().complete();

        for (DataCenter dataCenter : dataCenters) {
            System.out.println("Datacenter " + dataCenter.name() + " of Region " + dataCenter.region());
            for (World world : dataCenter.worlds()) {
                System.out.println("  World " + world.toString());
            }
        }

        for (DataCenter dataCenter : dataCenters) {
            System.out.println("// Region " + dataCenter.region());
            System.out.println("public static final class %s implements DataCenter".formatted(dataCenter.name()));
            StringJoiner worldJoiner = new StringJoiner(",");
            for (World world : dataCenter.worlds()) {
                String worldCode = """
                                   /**
                                    * The world %s
                                    */
                                   public final World %s = World.of("%s", %s);
                                               """.formatted(world.name(), world.name()
                                                                                .toUpperCase(Locale.ROOT), world.name(), world.id())
                                                  .stripIndent();
                System.out.printf(worldCode);
                worldJoiner.add(world.name().toUpperCase());
            }
            String end = """
                                     @Override
                                     public List<World> worlds() {
                                         return List.of(%s);
                                     }
                                     
                                     @Override
                                     public int id() {
                                         return %d;
                                     }
                         """.formatted(worldJoiner.toString(), -1);
            System.out.println(end);
            System.out.println("}");
        }
    }
}
