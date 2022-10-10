/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.shared.World;

import java.util.List;

public interface Datacenter extends WorldProvider {
    /**
     * Get a list of all worlds in this datacenter.
     *
     * @return unmodifiable world list
     */
    @Override
    List<World> worlds();
}
