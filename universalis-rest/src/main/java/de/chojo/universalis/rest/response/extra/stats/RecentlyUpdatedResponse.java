/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.routes.api.extra.stats.RecentlyUpdatedRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response for a {@link RecentlyUpdatedRequest}
 *
 * @param items recently updated items, without the world or time of the update
 */
public record RecentlyUpdatedResponse(@JsonProperty("items") List<Item> items) implements Iterable<Item> {
    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
