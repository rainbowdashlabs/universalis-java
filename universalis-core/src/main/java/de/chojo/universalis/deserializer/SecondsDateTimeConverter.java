/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import tools.jackson.databind.util.StdConverter;

import java.time.Instant;

/**
 * Deserializer for a Unix-epoch seconds value into a UTC-anchored
 * {@link Instant}.
 *
 * <p>Universalis sends timestamps as seconds since the UNIX epoch,
 * which is UTC by definition.
 */
public class SecondsDateTimeConverter extends StdConverter<Long, Instant> {
    @Override
    public Instant convert(Long value) {
        return Instant.ofEpochSecond(value);
    }
}
