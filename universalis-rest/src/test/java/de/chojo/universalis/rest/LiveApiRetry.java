/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

import org.slf4j.Logger;

import java.time.Duration;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Retries an assertion block against the live universalis API a small number of times
 * before failing. Use to reduce noise from transient network / upstream flakiness.
 * <p>
 * For plain {@code @Test} methods prefer {@code @RetryingTest(3)} from junit-pioneer —
 * this helper exists because pioneer's annotation does not compose with
 * {@code @ParameterizedTest}.
 */
public final class LiveApiRetry {
    private static final Logger log = getLogger(LiveApiRetry.class);
    private static final int DEFAULT_ATTEMPTS = 3;
    private static final Duration DEFAULT_DELAY = Duration.ofSeconds(2);

    private LiveApiRetry() {}

    /**
     * Runs {@code action}, retrying up to {@value #DEFAULT_ATTEMPTS} times if it throws.
     */
    public static void retry(ThrowingRunnable action) {
        retry(DEFAULT_ATTEMPTS, DEFAULT_DELAY, action);
    }

    /**
     * Runs {@code action}, retrying up to {@code attempts} times if it throws, waiting
     * {@code delay} between attempts.
     */
    public static void retry(int attempts, Duration delay, ThrowingRunnable action) {
        Throwable last = null;
        for (int attempt = 1; attempt <= attempts; attempt++) {
            try {
                action.run();
                return;
            } catch (Throwable t) {
                last = t;
                if (attempt < attempts) {
                    log.warn("Live API attempt {}/{} failed ({}), retrying in {}s",
                            attempt, attempts, t.getClass().getSimpleName(), delay.toSeconds());
                    try {
                        Thread.sleep(delay.toMillis());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new AssertionError("Interrupted while waiting to retry", e);
                    }
                }
            }
        }
        if (last instanceof RuntimeException re) throw re;
        if (last instanceof Error e) throw e;
        throw new AssertionError("Live API failed after " + attempts + " attempts", last);
    }

    /**
     * Runnable that can throw any exception.
     */
    @FunctionalInterface
    public interface ThrowingRunnable {
        void run() throws Throwable;
    }
}
