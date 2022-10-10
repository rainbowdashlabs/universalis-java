/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import de.chojo.universalis.entities.shared.World;

import java.util.List;

public interface WorldProvider {
    List<World> worlds();
}
