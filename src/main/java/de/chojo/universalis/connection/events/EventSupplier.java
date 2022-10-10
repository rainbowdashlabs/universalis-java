package de.chojo.universalis.connection.events;

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
     * @param itemNameSupplier item name supplier
     * @return event
     */
    T toEvent(NameSupplier itemNameSupplier);
}
