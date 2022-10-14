/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.worlds.World;

import java.util.List;

public record DataCenter(@JsonProperty("name") String name,
                         @JsonProperty("region") String region,
                         @JsonProperty("worlds") List<World> worlds) {

}
