/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import de.chojo.universalis.entities.City;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;

import java.util.EnumMap;
import java.util.Map;

/**
 * Deserializer for {@link TaxRatesResponse}
 */
public class TaxRatesResponseDeserializer extends ValueDeserializer<TaxRatesResponse> {
    @Override
    public TaxRatesResponse deserialize(JsonParser p, DeserializationContext ctxt) {
        Map<City, Integer> taxRates = ctxt.readValue(p, ctxt.getTypeFactory()
                                                            .constructMapType(EnumMap.class, City.class, Integer.class));
        return new TaxRatesResponse(taxRates);
    }
}
