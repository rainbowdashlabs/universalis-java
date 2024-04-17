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
 * Provides buckets for rate limiting
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
        return Bucket.builder().addLimit(Bandwidth.builder().capacity(50).refillIntervally(50, Duration.ofSeconds(2)).build()).build();
    }
}
