/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.DataCenter;
import de.chojo.universalis.rest.routes.api.DataCentersRequest;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

/**
 * Response of a {@link DataCentersRequest}
 *
 * @param dataCenters date centers
 */
public record DataCentersResponse(List<DataCenter> dataCenters) implements Iterable<DataCenter> {

    @NotNull
    @Override
    public Iterator<DataCenter> iterator() {
        return dataCenters.iterator();
    }
}
