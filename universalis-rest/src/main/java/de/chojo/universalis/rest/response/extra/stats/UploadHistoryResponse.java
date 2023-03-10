/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
