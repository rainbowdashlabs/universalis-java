/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

import java.time.Duration;

/**
 * Provides buckets for ratelimiting
 */
public final class Buckets {
    private Buckets() {
        throw new UnsupportedOperationException("This is a utility class.");
    }

    /**
     * Bucket preconfigured for universalis
     *
     * @return bucket
     */
    public static Bucket newUniversalisBucket() {
        return Bucket.builder().addLimit(Bandwidth.simple(50, Duration.ofSeconds(2))).build();
    }
}
