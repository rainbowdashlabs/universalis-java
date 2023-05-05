/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.entities.views.WorldUploadCountView;
import de.chojo.universalis.rest.routes.api.extra.WorldUploadCountsRequest;
import de.chojo.universalis.worlds.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

/**
 * Response for a {@link WorldUploadCountsRequest}
 *
 * @param worlds worlds
 */
public record WorldUploadCountResponse(
        Map<World, WorldUploadCountView> worlds) implements Iterable<Map.Entry<World, WorldUploadCountView>> {
    /**
     * Counts for a world
     *
     * @param world world
     * @return counts
     */
    public WorldUploadCountView counts(World world) {
        return worlds.get(world);
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<World, WorldUploadCountView>> iterator() {
        return worlds.entrySet().iterator();
    }
}
