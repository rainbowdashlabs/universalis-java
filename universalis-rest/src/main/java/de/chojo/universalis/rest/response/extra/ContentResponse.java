/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.response.extra;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO: Largely volatile
public record ContentResponse(@JsonProperty("id") int id) {
}
