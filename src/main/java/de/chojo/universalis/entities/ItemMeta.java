/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.connection.events.concrete.listing.views.MateriaView;

import java.util.List;

public record ItemMeta(boolean hq, @com.fasterxml.jackson.annotation.JsonProperty("isCrafted") boolean crafted,
                       int stainId, List<MateriaView> materia) {
}
