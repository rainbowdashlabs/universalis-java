/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.worlds.World;

import java.time.LocalDateTime;

/**
 * A minimized sale object
 *
 * @param hq          hq
 * @param price       price
 * @param timestamp   timestamp
 * @param onMannequin Whether this was purchased from a mannequin.
 * @param world       world
 * @param buyerName   The buyer's character name. This may be null.
 */
public record MinimizedSale(boolean hq,
                            MinimizedPrice price,
                            @JsonDeserialize(converter = SecondsDateTimeConverter.class) LocalDateTime timestamp,
                            boolean onMannequin,
                            World world,
                            String buyerName) {
}
