/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.worlds.Datacenter;
import de.chojo.universalis.worlds.Region;

import java.util.List;

public record DataCenter(@JsonProperty("name") Datacenter name, @JsonProperty("region") Region region, @JsonProperty("worlds") List<World> worlds) {

}
