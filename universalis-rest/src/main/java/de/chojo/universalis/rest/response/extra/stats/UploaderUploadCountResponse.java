/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.entities.views.SourceUploadCountView;
import de.chojo.universalis.rest.routes.api.extra.stats.UploaderUploadCountsRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response for a {@link UploaderUploadCountsRequest}
 *
 * @param uploader uploader count
 */
public record UploaderUploadCountResponse(
        List<SourceUploadCountView> uploader) implements Iterable<SourceUploadCountView> {
    @NotNull
    @Override
    public Iterator<SourceUploadCountView> iterator() {
        return uploader.iterator();
    }
}
