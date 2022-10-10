/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.provider;

import de.chojo.universalis.entities.shared.Name;

public interface NameSupplier {
    Name fromId(int id);
}
