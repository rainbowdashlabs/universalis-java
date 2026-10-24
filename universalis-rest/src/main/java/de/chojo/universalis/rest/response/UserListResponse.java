/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.deserializer.MillisDateTimeConverter;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.routes.api.UserListRequest;
import org.jetbrains.annotations.NotNull;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.Iterator;
import java.util.List;

/**
 * Response for a {@link UserListRequest}
 *
 * @param id      id of the list
 * @param created when the list was created
 * @param updated when the list was last changed
 * @param name    name of the list
 * @param items   items on the list
 */
public record UserListResponse(
        @JsonProperty("id") String id,

        @JsonProperty("created") @JsonDeserialize(converter = MillisDateTimeConverter.class)
        Instant created,

        @JsonProperty("updated") @JsonDeserialize(converter = MillisDateTimeConverter.class)
        Instant updated,

        @JsonProperty("name") String name,
        @JsonProperty("itemIDs") List<Item> items)
        implements Iterable<Item> {
    @NotNull
    @Override
    public Iterator<Item> iterator() {
        return items.iterator();
    }
}
