/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response;

import de.chojo.universalis.entities.City;

import java.util.Map;

public record TaxRatesResponse(Map<City, Integer> rates) {
    public int rate(City city) {
        return rates.get(city);
    }
}
