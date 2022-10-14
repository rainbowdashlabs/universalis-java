/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.entities.WorldItemRecently;
import de.chojo.universalis.rest.routes.api.extra.stats.LeastRecentlyUpdatedRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response for a {@link LeastRecentlyUpdatedRequest}
 *
 * @param items items
 */
public record LeastRecentlyUpdatedResponse(List<WorldItemRecently> items) implements Iterable<WorldItemRecently> {
    @NotNull
    @Override
    public Iterator<WorldItemRecently> iterator() {
        return items.iterator();
    }
}
