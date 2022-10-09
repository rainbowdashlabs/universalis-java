/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Name(@JsonProperty("en") String english,
                   @JsonProperty("de") String german,
                   @JsonProperty("fr") String french,
                   @JsonProperty("ja") String japanese) {
}
