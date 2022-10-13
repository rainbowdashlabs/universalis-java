/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

import org.jetbrains.annotations.NotNull;

/**
 * An enum representing cities which can have retainers.
 */
public enum City {
    /**
     * Limsa Lominsa
     */
    LIMSA_LOMINSA(1, "Limsa Lominsa"),
    /**
     * Gridania
     */
    GRIDANIA(2, "Gridania"),
    /**
     * Ul'dah
     */
    UL_DAH(3, "Ul'dah"),
    /**
     * Ishgard
     */
    ISHGARD(4, "Ishgard"),
    /**
     * Kugane
     */
    KUGANE(7, "Kugane"),
    /**
     * Crystarium
     */
    CRYSTARIUM(10, "Crystarium"),
    OLD_SHARLAYAN(-1, "Old Sharlayan");

    private final int id;
    private final String name;

    City(int id, String name) {
        this.id = id;
        this.name = name;
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
     * Get a city by its name
     *
     * @param name name
     * @return city
     * @throws IllegalArgumentException when name is invalid
     */
    public static City fromName(String name) {
        for (City city : values()) {
            if (city.cityName().equalsIgnoreCase(name)) return city;
        }
        throw new IllegalArgumentException("City " + name + " is unkown.");
    }

    @NotNull
    public String cityName() {
        return name;
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
