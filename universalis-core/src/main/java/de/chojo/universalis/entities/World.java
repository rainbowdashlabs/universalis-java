/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.worlds.WorldProvider;

import java.util.Collections;
import java.util.List;

public record World(@JsonProperty("name") String name,@JsonProperty("id") int id) implements WorldProvider {

    /**
     * Creates a new world based on a name and an id
     *
     * @param name world name
     * @param id   world id
     * @return new world instance
     */
    public static World of(String name, int id) {
        return new World(name, id);
    }

    @Override
    public List<World> worlds() {
        return Collections.singletonList(this);
    }
}
