/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.items;

import de.chojo.universalis.entities.Name;

public interface NameSupplier {
    Name fromId(int id);
}
