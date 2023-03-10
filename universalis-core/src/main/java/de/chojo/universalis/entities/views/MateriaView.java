/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
