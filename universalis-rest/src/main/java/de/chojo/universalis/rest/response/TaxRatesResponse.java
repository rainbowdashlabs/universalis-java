/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.City;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Map;

public record TaxRatesResponse(Map<City, Integer> rates) implements Iterable<Map.Entry<City, Integer>> {
    public int rate(City city) {
        return rates.get(city);
    }

    @NotNull
    @Override
    public Iterator<Map.Entry<City, Integer>> iterator() {
        return rates.entrySet().iterator();
    }
}
