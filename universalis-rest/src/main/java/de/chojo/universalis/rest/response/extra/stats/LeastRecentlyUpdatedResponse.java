/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra.stats;

import de.chojo.universalis.entities.WorldItemRecently;

import java.util.List;

public record LeastRecentlyUpdatedResponse(List<WorldItemRecently> items) {

}
