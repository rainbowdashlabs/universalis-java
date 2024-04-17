/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.worlds.World;

import java.util.List;

/**
 * A data center entity
 *
 * @param name   name
 * @param region region
 * @param worlds worlds
 */
public record DataCenter(@JsonProperty("name") String name,
                         @JsonProperty("region") String region,
                         @JsonProperty("worlds") List<World> worlds) {

}
