/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An average sale price.
 *
 * @param price average price per unit
 */
public record AveragePrice(@JsonProperty("price") double price) {}
