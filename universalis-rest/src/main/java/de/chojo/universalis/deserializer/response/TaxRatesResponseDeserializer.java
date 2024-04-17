/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.rest.response.TaxRatesResponse;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

/**
 * Deserializer for {@link TaxRatesResponse}
 */
public class TaxRatesResponseDeserializer extends JsonDeserializer<TaxRatesResponse> {
    @Override
    public TaxRatesResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<City, Integer> taxRates = ctxt.readValue(p, ctxt.getTypeFactory()
                                                            .constructMapType(EnumMap.class, City.class, Integer.class));
        return new TaxRatesResponse(taxRates);
    }
}
