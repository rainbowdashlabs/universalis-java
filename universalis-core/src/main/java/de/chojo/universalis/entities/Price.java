/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * Price of a listing
 *
 * @param pricePerUnit unit price
 * @param quantity     quantity
 * @param total        total
 */
public record Price(int pricePerUnit,
                    int quantity,
                    int total) {
}
