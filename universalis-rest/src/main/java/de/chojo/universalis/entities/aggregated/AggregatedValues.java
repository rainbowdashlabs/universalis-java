/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.aggregated;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.Nullable;

/**
 * Aggregated values of one quality. Average price and sale velocity cover the sales of the last 4 days. A value is
 * null when Universalis has no data for it.
 *
 * @param minListing        cheapest listing
 * @param medianListing     median listing price
 * @param recentPurchase    most recent sale
 * @param averageSalePrice  average sale price
 * @param dailySaleVelocity units sold per day
 */
public record AggregatedValues(
        @JsonProperty("minListing") @Nullable Scoped<ListingPrice> minListing,
        @JsonProperty("medianListing") @Nullable Scoped<ListingPrice> medianListing,
        @JsonProperty("recentPurchase") @Nullable Scoped<Purchase> recentPurchase,
        @JsonProperty("averageSalePrice") @Nullable Scoped<AveragePrice> averageSalePrice,
        @JsonProperty("dailySaleVelocity") @Nullable Scoped<SaleVelocity> dailySaleVelocity) {}
