/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
