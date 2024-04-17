/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api;

import de.chojo.universalis.rest.requests.Request;
import de.chojo.universalis.rest.response.HistoryResponse;
import de.chojo.universalis.rest.response.MarketBoardResponse;
import de.chojo.universalis.rest.routes.api.base.LimitedRequest;
import de.chojo.universalis.rest.routes.api.history.RegionHistoryRequest;
import de.chojo.universalis.rest.routes.requests.HistoryRequestImpl;

import org.jetbrains.annotations.CheckReturnValue;
import java.time.Duration;

/**
 * Base implementation for a {@link HistoryRequestImpl}
 */
public interface HistoryRequest extends Request<HistoryResponse>, RegionHistoryRequest, LimitedRequest<HistoryRequest> {
    /**
     * {@inheritDoc}
     * <p>
     * Default: 1800 Max: 999999
     */
    @Override
    @CheckReturnValue
    HistoryRequest limit(int limit);

    /**
     * The amount of time before now to take entries within the history data returned by {@link MarketBoardResponse#recentHistory()}.
     *
     * @param duration duration
     * @return request
     */
    @CheckReturnValue
    HistoryRequest historyTime(Duration duration);

    /**
     * The amount of time before now to calculate stats over. By default, this is 7 days.
     * <p>
     * Affects {@link MarketBoardResponse#saleVelocity()}
     *
     * @param duration duration
     * @return request
     */
    @CheckReturnValue
    HistoryRequest statsTime(Duration duration);
}
