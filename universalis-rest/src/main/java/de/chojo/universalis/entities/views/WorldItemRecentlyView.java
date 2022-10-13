/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.World;

public record WorldItemRecentlyView(@JsonProperty("itemID") int id,
                                    @JsonProperty("lastUploadTime") long updated,
                                    @JsonProperty("worldID") World world,
                                    @JsonProperty("worldName") String worldName) {

}
