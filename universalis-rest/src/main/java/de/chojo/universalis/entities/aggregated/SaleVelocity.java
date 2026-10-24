/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * How many units are sold per day.
 *
 * @param quantity units per day
 */
public record SaleVelocity(@JsonProperty("quantity") double quantity) {}
