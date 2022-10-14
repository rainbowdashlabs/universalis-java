/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

/**
 * Indicator for providing data based on the quality of an item
 *
 * @param general       general data
 * @param normalQuality normal quality data
 * @param highQuality   high quality data
 * @param <T>           type of indicator data
 */
public record QualityIndicator<T>(T general, T normalQuality, T highQuality) {

    /**
     * Create a new quality indicator
     *
     * @param general       general data
     * @param normalQuality normal quality data
     * @param highQuality   high quality data
     * @param <T>           type of indicator data
     * @return new indicator
     */
    public static <T> QualityIndicator<T> of(T general, T normalQuality, T highQuality) {
        return new QualityIndicator<>(general, normalQuality, highQuality);
    }
}
