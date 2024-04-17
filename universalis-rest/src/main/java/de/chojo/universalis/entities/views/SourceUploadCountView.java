/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

/**
 * <a href="https://docs.universalis.app/#schema-sourceuploadcountview">See on universalis</a>
 *
 * @param sourceName  The name of the client application.
 * @param uploadCount The number of uploads originating from the client application.
 */
public record SourceUploadCountView(String sourceName, String uploadCount) {
}
