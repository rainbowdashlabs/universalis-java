/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.routes.api.MarketableRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response of a {@link MarketableRequest}
 *
 * @param items items
 */
public record MarketableResponse(List<Item> items) implements Iterable<Item> {
    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
