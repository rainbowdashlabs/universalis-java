/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.subscriber;

/**
 * Interface providing valid subscription channels
 */
public interface Subscriptions {
    static Subscription listingAdd() {
        return new Subscription("listings/add");
    }

    static Subscription listingRemove() {
        return new Subscription("listings/remove");
    }

    static Subscription salesAdd() {
        return new Subscription("sales/add");
    }

    static Subscription salesRemove() {
        return new Subscription("sales/remove");
    }
}
