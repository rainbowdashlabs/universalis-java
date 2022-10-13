/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.MinimizedSale;
import de.chojo.universalis.entities.MinizedPrice;
import de.chojo.universalis.entities.World;

import javax.annotation.Nullable;
import java.time.Instant;

public record MinimizedSaleView(@JsonProperty("hq") boolean hq,
                                @JsonProperty("pricePerUnit") int pricePerUnit,
                                @JsonProperty("quantity") int quantity,
                                @JsonProperty("timestamp") Instant timestamp,
                                @JsonProperty("onMannequin") boolean onMannequin,
                                @JsonProperty("worldID") World world,
                                @JsonProperty("buyerName") @Nullable String buyerName) {
    public MinimizedSale toSale() {
        return new MinimizedSale(hq, new MinizedPrice(pricePerUnit, quantity), timestamp, onMannequin, world, buyerName);
    }
}
