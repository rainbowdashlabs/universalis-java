/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import java.util.Collections;
import java.util.List;

/**
 * Represents a world.
 */
public interface World extends WorldProvider {

    /**
     * Creates a new world based on a name and an id
     *
     * @param name world name
     * @param id   world id
     * @param dataCenter datacenter of world
     * @return new world instance
     */
    static World of(String name, int id, DataCenter dataCenter) {
        return new World() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public int id() {
                return id;
            }

            @Override
            public List<World> worlds() {
                return Collections.singletonList(this);
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof World world)) return false;
                return id == world.id();
            }

            @Override
            public DataCenter dataCenter() {
                return dataCenter;
            }

            @Override
            public String toString() {
                return "%s (%d)".formatted(name, id);
            }
        };
    }

    /**
     * Name of the world.
     * <p>
     * The name might be empty if the world was unknown.
     *
     * @return name
     */
    String name();

    /**
     * ID of the world.
     * <p>
     * This id might be -1 if the world was unknown
     *
     * @return id
     */
    int id();

    /**
     * The data center of the world
     *
     * @return data center
     */
    DataCenter dataCenter();
}
