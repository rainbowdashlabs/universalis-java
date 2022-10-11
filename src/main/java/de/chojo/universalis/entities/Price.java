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
 * @param total        total
 */
public record Price(int pricePerUnit,
                    int quantity,
                    int total) {
}
