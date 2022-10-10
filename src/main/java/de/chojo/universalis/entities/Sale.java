/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import de.chojo.universalis.entities.shared.Price;
import de.chojo.universalis.entities.shared.World;

import java.time.Instant;

public record Sale(boolean hq,
                   Price price,
                   Instant timestamp,
                   boolean onMannequin,
                   World world,
                   String buyerName) {
}
