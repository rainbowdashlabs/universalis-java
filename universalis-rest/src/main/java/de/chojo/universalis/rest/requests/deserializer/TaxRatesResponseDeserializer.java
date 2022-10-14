/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.rest.response.TaxRatesResponse;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

public class TaxRatesResponseDeserializer extends JsonDeserializer<TaxRatesResponse> {
    @Override
    public TaxRatesResponse deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Map<City, Integer> taxRates = ctxt.readValue(p, ctxt.getTypeFactory()
                                                            .constructMapType(EnumMap.class, City.class, Integer.class));
        return new TaxRatesResponse(taxRates);
    }
}
