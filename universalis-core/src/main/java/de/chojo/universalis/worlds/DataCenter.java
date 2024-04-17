/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import java.util.List;

/**
 * Represents a data center container multiple worlds
 */
public interface DataCenter extends WorldProvider {
    /**
     * Get a list of all worlds in this data center.
     *
     * @return unmodifiable world list
     */
    @Override
    List<World> worlds();

    /**
     * ID of the data center
     *
     * @return id
     */
    int id();

    /**
     * Name of the data center
     *
     * @return name
     */
    default String name() {
        return getClass().getSimpleName();
    }

    /**
     * Region of the datacenter
     *
     * @return region
     */
    Region region();
}
