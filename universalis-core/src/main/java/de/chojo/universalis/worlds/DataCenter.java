/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import java.util.List;

/**
 * Represetns a data center container multiple worlds
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
