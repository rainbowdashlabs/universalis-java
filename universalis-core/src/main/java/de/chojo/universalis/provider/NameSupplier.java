/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.Name;

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
}
