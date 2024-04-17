/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

/**
 * <a href="https://docs.universalis.app/#schema-worlduploadcountview">See on universalis</a>
 *
 * @param count      The number of times an upload has occurred on this world.
 * @param proportion The proportion of uploads on this world to the total number of uploads.
 */
public record WorldUploadCountView(int count, double proportion) {
}
