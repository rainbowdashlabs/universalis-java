/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public record UploadHistoryResponse(
        @JsonProperty("uploadCountByDay") List<Integer> uploadCountByDay) implements Iterable<Integer> {
    @NotNull
    @Override
    public Iterator<Integer> iterator() {
        return uploadCountByDay.iterator();
    }
}
