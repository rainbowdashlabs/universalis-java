/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
