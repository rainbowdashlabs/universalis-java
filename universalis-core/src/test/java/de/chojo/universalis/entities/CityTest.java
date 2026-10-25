/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityTest {
    @Test
    void noCityResolvesToUnknown() {
        assertEquals(City.UNKNOWN, City.fromId(0));
    }

    @Test
    void knownIdResolvesToCity() {
        assertEquals(City.TULIYOLLAL, City.fromId(14));
    }

    @Test
    void unknownIdResolvesToUnknown() {
        assertEquals(City.UNKNOWN, City.fromId(99));
    }
}
