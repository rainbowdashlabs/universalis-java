/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.sales.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import java.time.Instant;

public record SaleView(@JsonProperty("hq") boolean hq,
                       @JsonProperty("pricePerUnit") int pricePerUnit,
                       @JsonProperty("quantity") int quantity,
                       @JsonProperty("timestamp") long timestamp,
                       @JsonProperty("onMannequin") boolean onMannequin,
                       @JsonProperty("worldName") String worldName,
                       @JsonProperty("worldID") int worldId,
                       @JsonProperty("buyerName") String buyerName,
                       @JsonProperty("total") int total) {

    /**
     * Converts the {@link SaleView} to a {@link Sale} object.
     *
     * @param world world for the sale
     * @return list of sales
     */
    public Sale toSale(World world) {
        return new Sale(hq,
                new Price(pricePerUnit, quantity, total),
                Instant.ofEpochSecond(timestamp),
                onMannequin,
                world,
                buyerName);
    }
}
