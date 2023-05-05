/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.Name;

import java.util.Map;
import java.util.Optional;

/**
 * Interface to supply entity names based on an id
 */
public interface NameSupplier {
    /**
     * Returns the name based on an id
     *
     * @param id id
     * @return name
     */
    Name fromId(int id);

    Optional<Integer> fromName(String name);

    Map<Integer, Name> ids();

    Map<String, Integer> en();

    Map<String, Integer> de();

    Map<String, Integer> fr();

    Map<String, Integer> jp();
}
