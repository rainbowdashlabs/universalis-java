/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.extra;

import de.chojo.universalis.rest.UniversalisRestImpl;

public class Content {
    UniversalisRestImpl rest;
    public EntityRequest content(){
        return new EntityRequest(rest);
    }
}
