/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.rest.routes.api.extra.stats.UploadHistoryRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response for a {@link UploadHistoryRequest}
 *
 * @param uploadCountByDay upload counts by day
 */
public record UploadHistoryResponse(
        @JsonProperty("uploadCountByDay") List<Integer> uploadCountByDay) implements Iterable<Integer> {
    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return uploadCountByDay.iterator();
    }
}
