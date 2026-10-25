/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
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
     * Unknown city
     */
    UNKNOWN(-1, "Unknown"),
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
    OLD_SHARLAYAN(12, "Old Sharlayan"),
    /**
     * Tuliyollal
     **/
    TULIYOLLAL(14, "Tuliyollal");

    private static final Logger log = getLogger(City.class);
    private static final int NO_CITY = 0;

    private final int id;
    private final String name;

    City(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Get a city by its id.
     * <p>
     * Universalis reports {@code 0} for listings without a known retainer city. That id resolves to
     * {@link #UNKNOWN} silently; any other unknown id is logged, since it usually means a new city.
     *
     * @param id id
     * @return city, or {@link #UNKNOWN} when the id is not known
     */
    public static City fromId(int id) {
        if (id == NO_CITY) return UNKNOWN;
        for (City city : values()) {
            if (city.id() == id) return city;
        }
        log.error("ID {} is unknown.", id);
        return UNKNOWN;
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
        log.error("Name {} is unknown.", name);
        return UNKNOWN;
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
