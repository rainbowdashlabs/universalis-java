/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
