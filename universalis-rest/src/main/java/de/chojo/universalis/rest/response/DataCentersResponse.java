/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
