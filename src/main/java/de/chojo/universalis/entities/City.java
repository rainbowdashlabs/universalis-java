/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * An enum representing cities which can have retainers.
 */
public enum City {
    /**
     * Limsa Lominsa
     */
    LIMSA_LOMINSA(1),
    /**
     * Gridania
     */
    GRIDANIA(2),
    /**
     * Ul'dah
     */
    UL_DAH(3),
    /**
     * Ishgard
     */
    ISHGARD(4),
    /**
     * Kugane
     */
    KUGANE(7),
    /**
     * Crystarium
     */
    CRYSTARIUM(10);

    private final int id;

    City(int id) {
        this.id = id;
    }

    /**
     * Get a city by its id
     *
     * @param id id
     * @return city
     * @throws IllegalArgumentException when id is invalid
     */
    public static City fromId(int id) {
        for (City city : values()) {
            if (city.id() == id) return city;
        }
        throw new IllegalArgumentException("ID " + id + " is unkown.");
    }

    /**
     * The city id.
     *
     * @return id
     */
    public int id() {
        return id;
    }
}
