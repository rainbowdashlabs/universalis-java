/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.websocket.events.concrete.sales.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * <a href="https://docs.universalis.app/#schema-saleview">See on universalis</a>
 * @param hq  Whether the item was high-quality.
 * @param pricePerUnit The price per unit sold.
 * @param quantity The stack size sold.
 * @param timestamp The sale time, in seconds since the UNIX epoch.
 * @param onMannequin Whether this was purchased from a mannequin.
 * @param worldName The world name, if applicable.
 * @param worldId The world ID, if applicable.
 * @param buyerName The buyer name.
 * @param total The total price.
 */
public record SaleView(@JsonProperty("hq") boolean hq,
                       @JsonProperty("pricePerUnit") int pricePerUnit,
                       @JsonProperty("quantity") int quantity,
                       @JsonProperty("timestamp")  @JsonDeserialize(converter = SecondsDateTimeConverter.class) LocalDateTime timestamp,
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
                timestamp,
                onMannequin,
                world,
                buyerName);
    }
}
