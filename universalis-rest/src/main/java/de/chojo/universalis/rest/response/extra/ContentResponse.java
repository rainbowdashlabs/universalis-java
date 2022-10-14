/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra;

import java.util.Map;

//TODO: Probably a no fix. Dunno what it does and I dont need the data as it seems ¯\_(ツ)_/¯
public record ContentResponse(Map<String, Object> content) {
}
