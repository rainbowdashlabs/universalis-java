/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
