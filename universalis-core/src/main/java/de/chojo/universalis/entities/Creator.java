/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import javax.annotation.Nullable;

/**
 * Represents the creator of a listing
 *
 * @param name name of creator
 * @param id   hashed id of the creator
 */
public record Creator(@Nullable String name, String id) {
}
