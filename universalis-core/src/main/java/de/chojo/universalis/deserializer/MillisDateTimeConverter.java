/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
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
