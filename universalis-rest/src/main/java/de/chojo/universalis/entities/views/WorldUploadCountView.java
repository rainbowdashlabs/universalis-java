/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
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
