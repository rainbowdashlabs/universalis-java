/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.marketboard;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.routes.api.MarketBoardRequest;

import java.util.Collection;

public interface RegionMarketBoardRequest extends BlankMarketBoardRequest {
    MarketBoardRequest items(Item... items);

    MarketBoardRequest items(Collection<Item> items);

    MarketBoardRequest itemsIds(Integer... itemIds);

    MarketBoardRequest itemsIds(Collection<Integer> itemIds);
}
