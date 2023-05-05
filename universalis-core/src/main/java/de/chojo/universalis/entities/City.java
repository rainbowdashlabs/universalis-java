/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * An enum representing cities which can have retainers.
 */
public enum City {
    /**
     * Limsa Lominsa
     */
    UNKOWN(-1, "Unkown"),
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
    /**
     * Old Sharlayan
     */
    OLD_SHARLAYAN(12, "Old Sharlayan");

    private static final Logger log = getLogger(City.class);

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
        log.error("ID {} is unkown.", id);
        return UNKOWN;
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
        log.error("Name {} is unkown.", name);
        return UNKOWN;
    }

    /**
     * Name of the city
     *
     * @return name
     */
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
