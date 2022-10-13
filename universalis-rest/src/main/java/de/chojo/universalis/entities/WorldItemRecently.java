/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record WorldItemRecently(@JsonProperty("itemID") Item item,
                                @JsonProperty("lastUploadTime") Instant updated,
                                @JsonProperty("worldID") World world) {
}
