/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.aggregated.AggregatedItem;
import de.chojo.universalis.rest.routes.api.AggregatedRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response for an {@link AggregatedRequest}
 *
 * @param items       data per item
 * @param failedItems requested items Universalis has no data for
 */
public record AggregatedResponse(
        @JsonProperty("results") List<AggregatedItem> items,
        @JsonProperty("failedItems") List<Item> failedItems) implements Iterable<AggregatedItem> {
    @NotNull
    @Override
    public Iterator<AggregatedItem> iterator() {
        return items.iterator();
    }
}
