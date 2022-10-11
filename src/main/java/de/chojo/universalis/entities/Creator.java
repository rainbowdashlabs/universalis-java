/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * Represents the creator of a listing
 *
 * @param name name of creator
 * @param id   hashed id of the creator
 */
public record Creator(String name, String id) {
}
