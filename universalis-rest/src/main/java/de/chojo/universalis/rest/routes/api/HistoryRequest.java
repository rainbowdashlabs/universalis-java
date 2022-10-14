/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.history.RegionHistoryRequest;

import java.time.Duration;

public interface HistoryRequest extends Request<HistoryResponse>, RegionHistoryRequest {
    /**
     * The number of entries to return. By default, this is set to 1800, but may be set to a maximum of 999999.
     *
     * @param limit limit
     * @return request
     */
    HistoryRequest limit(int limit);

    /**
     * The amount of time before now to take entries within the history data returned by {@link MarketBoardResponse#recentHistory()}.
     *
     * @param duration duration
     * @return request
     */
    HistoryRequest historyTime(Duration duration);

    /**
     * The amount of time before now to calculate stats over. By default, this is 7 days.
     * <p>
     * Affects {@link MarketBoardResponse#saleVelocity()}
     *
     * @param duration duration
     * @return request
     */
    HistoryRequest statsTime(Duration duration);
}
