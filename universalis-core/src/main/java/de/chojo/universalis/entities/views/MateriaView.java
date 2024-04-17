/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Probably add some richer mapping to type and tier

/**
 * <a href="https://docs.universalis.app/#schema-materiaview">See on universalis</a>
 *
 * @param slotId    The materia slot.
 * @param materiaId The materia item ID.
 */
public record MateriaView(@JsonProperty("slotID") int slotId,
                          @JsonProperty("materiaID") int materiaId) {
}
