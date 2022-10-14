/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
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
