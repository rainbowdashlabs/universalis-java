/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities;

public record QualityIndicator<T>(T general, T normalQuality, T highQuality) {

    public static <T> QualityIndicator<T> of(T general, T normalQuality, T highQuality) {
        return new QualityIndicator<>(general, normalQuality, highQuality);
    }
}
