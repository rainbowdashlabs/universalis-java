/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.marketboard.RegionMarketBoardRequest;
import de.chojo.universalis.rest.routes.requests.MarketBoardRequestImpl;

import javax.annotation.CheckReturnValue;
import java.time.Duration;

/**
 * Base implementation for a {@link MarketBoardRequestImpl}
 */
public interface MarketBoardRequest extends Request<MarketBoardResponse>, RegionMarketBoardRequest {
    /**
     * The number of entries to return. By default, all listings will be returned.
     *
     * @param limit limit
     * @return request
     */
    @CheckReturnValue
    MarketBoardRequest listingsLimit(int limit);

    /**
     * The number of entries to return. By default, a maximum of 5 entries will be returned.
     *
     * @param limit limit
     * @return request
     */
    @CheckReturnValue
    MarketBoardRequest historyLimit(int limit);

    /**
     * If the result should not have Gil sales tax (GST) factored in.
     * GST is applied to all consumer purchases in-game, and is separate from the retainer city tax that impacts what sellers receive.
     * By default, GST is factored in.
     *
     * @return request
     * @deprecated Tax is no longer included in pricing. Tax is provided via {@link Listing#tax()}
     */
    @CheckReturnValue
    @Deprecated(forRemoval = true)
    MarketBoardRequest noGst();

    /**
     * If the result should not have Gil sales tax (GST) factored in.
     * GST is applied to all consumer purchases in-game, and is separate from the retainer city tax that impacts what sellers receive.
     * By default, GST is factored in.
     *
     * @param noGst set to true to disable GST
     * @return request
     * @deprecated Tax is no longer included in pricing. Tax is provided via {@link Listing#tax()}
     */
    @CheckReturnValue
    @Deprecated(forRemoval = true)
    MarketBoardRequest noGst(boolean noGst);

    /**
     * Filter for HQ listings and entries. By default, both HQ and NQ listings and entries will be returned.
     *
     * @return request
     */
    @CheckReturnValue
    default MarketBoardRequest highQuality() {
        return highQuality(true);
    }

    /**
     * Filter for NQ listings and entries. By default, both HQ and NQ listings and entries will be returned.
     *
     * @return request
     */
    @CheckReturnValue
    default MarketBoardRequest normalQuality() {
        return highQuality(false);
    }

    /**
     * Filter for HQ listings and entries. By default, both HQ and NQ listings and entries will be returned.
     *
     * @param highQuality set to true to only receive hq listings
     * @return request
     */
    @CheckReturnValue
    MarketBoardRequest highQuality(boolean highQuality);

    /**
     * The amount of time before now to take entries within the history data returned by {@link MarketBoardResponse#recentHistory()}.
     *
     * @param duration duration
     * @return request
     */
    @CheckReturnValue
    MarketBoardRequest historyTime(Duration duration);

    /**
     * The amount of time before now to calculate stats over. By default, this is 7 days.
     * <p>
     * Affects {@link MarketBoardResponse#saleVelocity()}
     *
     * @param duration duration
     * @return request
     */
    @CheckReturnValue
    MarketBoardRequest statsTime(Duration duration);
}
