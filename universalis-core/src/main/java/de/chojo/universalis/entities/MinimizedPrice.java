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
 */
public record MinimizedPrice(int pricePerUnit,
                             int quantity) {
    /**
     * The total price. Manually calculated.
     *
     * @return total price
     */
    public int total() {
        return pricePerUnit * quantity;
    }
}
