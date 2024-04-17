/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import de.chojo.universalis.deserializer.CityDeserializer;
import de.chojo.universalis.deserializer.EmptyStringDeserializer;
import de.chojo.universalis.deserializer.ItemDeserializer;
import de.chojo.universalis.deserializer.ListingDeserializer;
import de.chojo.universalis.deserializer.RegionDeserializer;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.deserializer.WorldDeserializer;
import de.chojo.universalis.deserializer.WorldKeyDeserializer;
import de.chojo.universalis.deserializer.response.ContentResponseDeserializer;
import de.chojo.universalis.deserializer.response.DataCenterResponseDeserializer;
import de.chojo.universalis.deserializer.response.HistoryResponseDeserializer;
import de.chojo.universalis.deserializer.response.MarketBoardResponseDeserializer;
import de.chojo.universalis.deserializer.response.MarketableResponseDeserializer;
import de.chojo.universalis.deserializer.response.TaxRatesResponseDeserializer;
import de.chojo.universalis.deserializer.response.UploaderUploadCountsResponseDeserializer;
import de.chojo.universalis.deserializer.response.WorldUploadCountResponseDeserializer;
import de.chojo.universalis.deserializer.response.WorldsResponseDeserializer;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.entities.Item;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.provider.NameSupplier;
import de.chojo.universalis.rest.response.DataCentersResponse;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.response.MarketableResponse;
import de.chojo.universalis.rest.response.TaxRatesResponse;
import de.chojo.universalis.rest.response.WorldsResponse;
import de.chojo.universalis.rest.response.extra.ContentResponse;
import de.chojo.universalis.rest.response.extra.stats.UploaderUploadCountResponse;
import de.chojo.universalis.rest.response.extra.stats.WorldUploadCountResponse;
import de.chojo.universalis.worlds.Region;
import de.chojo.universalis.worlds.World;

import java.time.Instant;

/**
 * Class providing preconfigured {@link ObjectMapper}
 */
public final class Mapper {
    private Mapper() {
        throw new UnsupportedOperationException("This is a utility class.");
    }

    /**
     * A mapper able to map items and everything used in rest responsed
     *
     * @param itemNameSupplier item name supplier
     * @return object mapper
     */
    public static ObjectMapper create(NameSupplier itemNameSupplier) {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(City.class, new CityDeserializer())
              .addDeserializer(ContentResponse.class, new ContentResponseDeserializer())
              //.addDeserializer(Datacenter.class, new DataCenterDeserializer())
              .addDeserializer(DataCentersResponse.class, new DataCenterResponseDeserializer())
              .addDeserializer(String.class, new EmptyStringDeserializer())
              .addDeserializer(HistoryResponse.class, new HistoryResponseDeserializer())
              //.addDeserializer(Instant.class, new SecondsDateTimeConverter())
              .addDeserializer(Item.class, new ItemDeserializer(itemNameSupplier))
              .addDeserializer(Listing.class, new ListingDeserializer())
              .addDeserializer(MarketableResponse.class, new MarketableResponseDeserializer())
              .addDeserializer(MarketBoardResponse.class, new MarketBoardResponseDeserializer())
              .addDeserializer(Region.class, new RegionDeserializer())
              .addDeserializer(TaxRatesResponse.class, new TaxRatesResponseDeserializer())
              .addDeserializer(UploaderUploadCountResponse.class, new UploaderUploadCountsResponseDeserializer())
              .addKeyDeserializer(World.class, new WorldKeyDeserializer())
              .addDeserializer(World.class, new WorldDeserializer())
              .addDeserializer(WorldsResponse.class, new WorldsResponseDeserializer())
              .addDeserializer(WorldUploadCountResponse.class, new WorldUploadCountResponseDeserializer());
        return new JsonMapper()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE)
                .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(module);
    }
}
