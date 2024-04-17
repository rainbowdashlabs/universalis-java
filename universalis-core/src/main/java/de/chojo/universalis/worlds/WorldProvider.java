/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import java.util.List;

/**
 * An interface which is used for entities that can provide one or more worlds.
 */
public interface WorldProvider {
    /**
     * The world provided by this entity
     *
     * @return list of worlds
     */
    List<World> worlds();
}
