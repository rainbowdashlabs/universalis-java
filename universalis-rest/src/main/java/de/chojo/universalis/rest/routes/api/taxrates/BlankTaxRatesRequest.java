/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.rest.routes.api.taxrates;

import de.chojo.universalis.worlds.World;
import de.chojo.universalis.rest.routes.api.TaxRatesRequest;

public interface BlankTaxRatesRequest {

    TaxRatesRequest world(World world);
}
