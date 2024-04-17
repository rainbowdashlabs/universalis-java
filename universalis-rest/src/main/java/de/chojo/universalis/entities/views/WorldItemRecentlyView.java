/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.worlds.World;

/**
 * <a href="https://docs.universalis.app/#schema-worlditemrecencyview">See on universalis</a>
 *
 * @param id      The item ID.
 * @param updated The last upload time for the item on the listed world.
 * @param world   The world ID.
 */
public record WorldItemRecentlyView(@JsonProperty("itemID") Item id,
                                    @JsonProperty("lastUploadTime") long updated,
                                    @JsonProperty("worldID") World world) {

}
