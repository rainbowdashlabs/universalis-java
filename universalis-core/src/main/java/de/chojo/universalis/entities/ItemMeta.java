/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.views.MateriaView;

import java.util.List;

public record ItemMeta(boolean hq, @JsonProperty("isCrafted") boolean crafted,
                       int stainId, List<MateriaView> materia) {
}
