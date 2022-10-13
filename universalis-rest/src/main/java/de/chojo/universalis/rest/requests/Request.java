/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.requests;

import java.util.concurrent.CompletableFuture;

public interface Request<T> {
    CompletableFuture<T> queue();

    T complete();
}
