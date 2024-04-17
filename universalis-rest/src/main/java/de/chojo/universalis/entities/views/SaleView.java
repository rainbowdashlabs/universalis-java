/*
 *     SPDX-License-Identifier: LGPL-3.0-or-later
 *
 *     Copyright (C) RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Sale;
import de.chojo.universalis.worlds.World;

import org.jetbrains.annotations.Nullable;
import java.time.LocalDateTime;

/**
 * <a href="https://docs.universalis.app/#schema-saleview">See on unversalis</a>
 *
 * @param hq           Whether the item was high-quality.
 * @param pricePerUnit The price per unit sold.
 * @param quantity     The stack size sold.
 * @param timestamp    The sale time
 * @param onMannequin  Whether this was purchased from a mannequin.
 * @param world        world
 * @param buyerName    The buyer name.
 * @param total        The total price.
 */
public record SaleView(@JsonProperty("hq") boolean hq,
                       @JsonProperty("pricePerUnit") int pricePerUnit,
                       @JsonProperty("quantity") int quantity,
                       @JsonProperty("timestamp") @JsonDeserialize(converter = SecondsDateTimeConverter.class) LocalDateTime timestamp,
                       @JsonProperty("onMannequin") boolean onMannequin,
                       @JsonProperty("worldID") World world,
                       @JsonProperty("buyerName") @Nullable String buyerName,
                       @JsonProperty("total") int total) {
    /**
     * Converts the view to a sale object
     *
     * @return sale object
     */
    public Sale toSale() {
        return new Sale(hq, new Price(pricePerUnit, quantity, total), timestamp, onMannequin, world, buyerName);
    }
}
