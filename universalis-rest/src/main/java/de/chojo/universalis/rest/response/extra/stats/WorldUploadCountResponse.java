/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.worlds.World;
import de.chojo.universalis.entities.views.WorldUploadCountView;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public record WorldUploadCountResponse(
        Map<World, WorldUploadCountView> worlds) implements Iterable<Map.Entry<World, WorldUploadCountView>> {
    public WorldUploadCountView counts(World world) {
        return worlds.get(world);
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<World, WorldUploadCountView>> iterator() {
        return worlds.entrySet().iterator();
    }
}
