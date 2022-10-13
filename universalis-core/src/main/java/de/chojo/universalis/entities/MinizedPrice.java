/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * Price of a listing
 *
 * @param pricePerUnit unit price
 * @param quantity     quanity
 */
public record MinizedPrice(int pricePerUnit,
                           int quantity) {
    public int total() {
        return pricePerUnit * quantity;
    }
}
