/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.entities.views.SourceUploadCountView;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record UploaderUploadCountResponse(
        List<SourceUploadCountView> uploader) implements Iterable<SourceUploadCountView> {
    @NotNull
    @Override
    public Iterator<SourceUploadCountView> iterator() {
        return uploader.iterator();
    }
}
