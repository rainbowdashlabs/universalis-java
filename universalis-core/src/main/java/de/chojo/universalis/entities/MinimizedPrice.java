/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * Price of a listing
 *
 * @param pricePerUnit unit price
 * @param quantity     quanity
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
