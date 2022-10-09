/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.worlds;

import java.util.Collections;
import java.util.List;

public record World(String name, int id) implements WorldProvider{

    public static World of(String name, int id) {
        return new World(name, id);
    }

    @Override
    public List<World> worlds() {
        return Collections.singletonList(this);
    }
}
