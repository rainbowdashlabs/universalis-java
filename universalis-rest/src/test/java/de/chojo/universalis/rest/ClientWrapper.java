/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest;

public class ClientWrapper {
    private static final UniversalisRest rest = UniversalisRest.defaultApi();

    public static UniversalisRest client() {
        return rest;
    }
}
