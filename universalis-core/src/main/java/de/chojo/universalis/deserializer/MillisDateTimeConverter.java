/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.deserializer;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * Deserializer for instant
 */
public class MillisDateTimeConverter extends StdConverter<Long, LocalDateTime> {
    @Override
    public LocalDateTime convert(Long value) {
        return Instant.ofEpochMilli(value).atZone(TimeZone.getDefault().toZoneId()).toLocalDateTime();
    }
}
