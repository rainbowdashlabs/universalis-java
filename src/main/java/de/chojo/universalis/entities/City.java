/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

public enum City {
    LIMSA_LOMINSA(1),
    GRIDANIA(2),
    UL_DAH(3),
    ISHGARD(4),
    KUGANE(7),
    CRYSTARIUM(10);

    private final int id;

    City(int id) {
        this.id = id;
    }

    public int id() {
        return id;
    }

    public static City fromId(int id) {
        for (City city : values()) {
            if (city.id() == id) return city;
        }
        throw new IllegalArgumentException("ID " + id + " is unkown.");
    }
}
