/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

public record Creator(String name, @com.fasterxml.jackson.annotation.JsonProperty("creatorID") String id) {
}
