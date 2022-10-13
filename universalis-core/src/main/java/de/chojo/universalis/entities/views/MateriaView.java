/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MateriaView(@JsonProperty("slotId") int slotId,
                          @JsonProperty("materiaId") int materiaId) {
}
