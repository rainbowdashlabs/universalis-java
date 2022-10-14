/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import javax.annotation.Nullable;
import java.time.Instant;

public record SaleView(@JsonProperty("hq") boolean hq,
                       @JsonProperty("pricePerUnit") int pricePerUnit,
                       @JsonProperty("quantity") int quantity,
                       @JsonProperty("timestamp") Instant timestamp,
                       @JsonProperty("onMannequin") boolean onMannequin,
                       @JsonProperty("worldID") World world,
                       @JsonProperty("buyerName") @Nullable String buyerName,
                       @JsonProperty("total") int total) {
    public Sale toSale() {
        return new Sale(hq, new Price(pricePerUnit, quantity, total), timestamp, onMannequin, world, buyerName);
    }
}
