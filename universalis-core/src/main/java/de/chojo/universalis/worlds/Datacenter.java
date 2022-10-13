/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.World;

import java.util.List;

public interface Datacenter extends WorldProvider {
    /**
     * Get a list of all worlds in this datacenter.
     *
     * @return unmodifiable world list
     */
    @Override
    List<World> worlds();

    /**
     * ID of the datacenter
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
}
