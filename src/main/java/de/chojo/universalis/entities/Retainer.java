/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * The retainer of this listing
 *
 * @param id   retainer id
 * @param name retainer name
 * @param city retainer city
 */
public record Retainer(String id, String name, City city) {
}
