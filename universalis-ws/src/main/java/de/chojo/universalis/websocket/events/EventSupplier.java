/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events;

import de.chojo.universalis.events.Event;
import de.chojo.universalis.provider.NameSupplier;

/**
 * Interface used to mark clases which can provide an interface out of their data
 *
 * @param <T> type of event
 */
public interface EventSupplier<T extends Event> {
    /**
     * Converts the websocket event into an internal event
     *
     * @return event
     */
    T toEvent();
}
