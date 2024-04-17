/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.rest;

public class ClientWrapper {
    private static final UniversalisRest rest = UniversalisRest.defaultApi();

    public static UniversalisRest client() {
        return rest;
    }
}
