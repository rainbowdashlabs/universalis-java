/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.base;

import de.chojo.universalis.entities.Item;
import org.jetbrains.annotations.CheckReturnValue;

import java.util.Collection;

/**
 * Interface for requests that take up to 100 items at once. The items can only be set once.
 *
 * @param <T> type of request builder
 */
public interface MultiItemScope<T> {
    /**
     * Items to look up
     *
     * @param items 1 to 100 items
     * @return request builder
     * @throws IllegalArgumentException if no item or more than 100 items are given
     * @throws IllegalStateException    if the items have already been set
     */
    @CheckReturnValue
    T items(Item... items);

    /**
     * Items to look up
     *
     * @param items 1 to 100 items
     * @return request builder
     * @throws IllegalArgumentException if no item or more than 100 items are given
     * @throws IllegalStateException    if the items have already been set
     */
    @CheckReturnValue
    T items(Collection<Item> items);

    /**
     * Item ids to look up
     *
     * @param itemIds 1 to 100 item ids
     * @return request builder
     * @throws IllegalArgumentException if no id or more than 100 ids are given
     * @throws IllegalStateException    if the items have already been set
     */
    @CheckReturnValue
    T itemsIds(Integer... itemIds);

    /**
     * Item ids to look up
     *
     * @param itemIds 1 to 100 item ids
     * @return request builder
     * @throws IllegalArgumentException if no id or more than 100 ids are given
     * @throws IllegalStateException    if the items have already been set
     */
    @CheckReturnValue
    T itemsIds(Collection<Integer> itemIds);
}
