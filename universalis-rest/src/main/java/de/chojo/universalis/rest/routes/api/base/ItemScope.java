/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.entities.Item;

import javax.annotation.CheckReturnValue;
import java.util.Collection;

/**
 * Interface for requests using items
 *
 * @param <T> type of request builder
 */
public interface ItemScope<T> {
    /**
     * Items to lookup
     *
     * @param items items
     * @return request builder
     */
    @CheckReturnValue
    T items(Item... items);

    /**
     * Items to lookup
     *
     * @param items items
     * @return request builder
     */
    @CheckReturnValue
    T items(Collection<Item> items);

    /**
     * Item ids to lookup
     *
     * @param itemIds item ids
     * @return request builder
     */
    @CheckReturnValue
    T itemsIds(Integer... itemIds);

    /**
     * Item ids to lookup
     *
     * @param itemIds item ids
     * @return request builder
     */
    @CheckReturnValue
    T itemsIds(Collection<Integer> itemIds);
}
