/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.entities.MinimizedPrice;
import de.chojo.universalis.entities.MinimizedSale;
import de.chojo.universalis.worlds.World;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * <a href="https://docs.universalis.app/#schema-minimizedsaleview">See on universalis</a>
 *
 * @param hq           Whether the item was high-quality.
 * @param pricePerUnit The price per unit sold.
 * @param quantity     The stack size sold.
 * @param timestamp    The sale time
 * @param onMannequin  Whether this was purchased from a mannequin.
 * @param world        world
 * @param buyerName    The buyer's character name.
 */
public record MinimizedSaleView(@JsonProperty("hq") boolean hq,
                                @JsonProperty("pricePerUnit") int pricePerUnit,
                                @JsonProperty("quantity") int quantity,
                                @JsonProperty("timestamp") @JsonDeserialize(converter = SecondsDateTimeConverter.class) LocalDateTime timestamp,
                                @JsonProperty("onMannequin") boolean onMannequin,
                                @JsonProperty("worldID") World world,
                                @JsonProperty("buyerName") @Nullable String buyerName) {
    /**
     * Converts to a minimized sale object
     *
     * @return new minimized sale object
     */
    public MinimizedSale toSale() {
        return new MinimizedSale(hq, new MinimizedPrice(pricePerUnit, quantity), timestamp, onMannequin, world, buyerName);
    }
}
