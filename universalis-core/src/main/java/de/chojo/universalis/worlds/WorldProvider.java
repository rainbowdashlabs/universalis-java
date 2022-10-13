/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.World;

import java.util.List;

/**
 * An interface which is used for entities that can provide one or more worlds.
 */
public interface WorldProvider {
    /**
     * The world provided by this entity
     * @return list of worlds
     */
    List<World> worlds();
}
