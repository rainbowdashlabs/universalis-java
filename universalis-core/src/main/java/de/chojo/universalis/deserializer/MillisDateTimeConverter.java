/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import tools.jackson.databind.util.StdConverter;

import java.time.Instant;

/**
 * Deserializer for instant
 */
public class MillisDateTimeConverter extends StdConverter<Long, Instant> {
    @Override
    public Instant convert(Long value) {
        return Instant.ofEpochMilli(value);
    }
}
