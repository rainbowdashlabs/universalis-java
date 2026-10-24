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
 * Interface for requests using items. A request takes exactly one item and the item can only be set once.
 *
 * @param <T> type of request builder
 */
public interface ItemScope<T> {
    /**
     * Item to look up
     *
     * @param items items
     * @return request builder
     * @throws IllegalArgumentException if not exactly one item is given
     * @throws IllegalStateException    if the item has already been set
     */
    @CheckReturnValue
    T items(Item... items);

    /**
     * Item to look up
     *
     * @param items items
     * @return request builder
     * @throws IllegalArgumentException if not exactly one item is given
     * @throws IllegalStateException    if the item has already been set
     */
    @CheckReturnValue
    T items(Collection<Item> items);

    /**
     * Item id to look up
     *
     * @param itemIds item ids
     * @return request builder
     * @throws IllegalArgumentException if not exactly one item is given
     * @throws IllegalStateException    if the item has already been set
     */
    @CheckReturnValue
    T itemsIds(Integer... itemIds);

    /**
     * Item id to look up
     *
     * @param itemIds item ids
     * @return request builder
     * @throws IllegalArgumentException if not exactly one item is given
     * @throws IllegalStateException    if the item has already been set
     */
    @CheckReturnValue
    T itemsIds(Collection<Integer> itemIds);
}
