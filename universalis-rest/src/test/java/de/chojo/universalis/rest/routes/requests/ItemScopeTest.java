/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.requests;

import de.chojo.universalis.rest.UniversalisRest;
import de.chojo.universalis.worlds.Worlds;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemScopeTest {
    private static final UniversalisRest rest = UniversalisRest.defaultApi();

    @AfterAll
    static void close() {
        rest.close();
    }

    @Test
    void marketBoardTakesOneItem() {
        assertDoesNotThrow(
                () -> rest.marketBoard().world(Worlds.europe().light().odin).itemsIds(5057));
    }

    @Test
    void marketBoardRejectsSeveralItems() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rest.marketBoard().world(Worlds.europe().light().odin).itemsIds(5057, 5058));
        assertThrows(
                IllegalArgumentException.class,
                () -> rest.marketBoard().world(Worlds.europe().light().odin).itemsIds(List.of()));
    }

    @Test
    void historyRejectsSeveralItems() {
        assertThrows(
                IllegalArgumentException.class,
                () -> rest.history().dataCenter(Worlds.europe().light()).itemsIds(List.of(5057, 5058)));
    }

    @Test
    void itemCanOnlyBeSetOnce() {
        assertThrows(
                IllegalStateException.class,
                () -> rest.marketBoard()
                        .world(Worlds.europe().light().odin)
                        .itemsIds(5057)
                        .itemsIds(5058));
    }
}
