/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.history;

import de.chojo.universalis.entities.Item;
import de.chojo.universalis.rest.routes.api.HistoryRequest;

import java.util.Collection;

public interface RegionHistoryRequest extends BlankHistoryRequest {
    HistoryRequest items(Item... items);

    HistoryRequest items(Collection<Item> items);

    HistoryRequest itemsIds(Integer... itemIds);

    HistoryRequest itemsIds(Collection<Integer> itemIds);
}
