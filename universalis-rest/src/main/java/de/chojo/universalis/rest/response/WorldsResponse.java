/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.rest.routes.api.WorldsRequest;
import de.chojo.universalis.worlds.World;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response of a {@link WorldsRequest}
 *
 * @param worlds worlds
 */
public record WorldsResponse(List<World> worlds) implements Iterable<World> {
    @NotNull
    @Override
    public Iterator<World> iterator() {
        return worlds.iterator();
    }
}
