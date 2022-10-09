/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.entities.Creator;
import de.chojo.universalis.entities.ItemMeta;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Retainer;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

import java.time.Instant;
import java.util.List;

public record ListingView(@JsonProperty("lastReviewTime") long lastReviewTime,
                          @JsonProperty("pricePerUnit") int pricePerUnit,
                          @JsonProperty("quantity") int quantity,
                          @JsonProperty("stainID") int stainId,
                          @JsonProperty("worldName") String worldName,
                          @JsonProperty("worldID") int worldId,
                          @JsonProperty("creatorName") String creatorName,
                          @JsonProperty("creatorID") String creatorId,
                          @JsonProperty("hq") boolean hq,
                          @JsonProperty("isCrafted") boolean isCrafted,
                          @JsonProperty("listingID") String listingId,
                          @JsonProperty("materia") List<MateriaView> materia,
                          @JsonProperty("onMannequin") boolean onManequin,
                          @JsonProperty("retainerCity") int retainerCity,
                          @JsonProperty("retainerID") String retainerId,
                          @JsonProperty("retainerName") String retainerName,
                          @JsonProperty("sellerID") String sellerId,
                          @JsonProperty("total") int total) {
    public Listing toEvent(World world) {
        return new Listing(Instant.ofEpochSecond(lastReviewTime),
                world,
                new Creator(creatorName, creatorId),
                new ItemMeta(hq, isCrafted, stainId, materia),
                listingId,
                onManequin,
                new Retainer(retainerId, retainerName, City.fromId(retainerCity)),
                sellerId,
                new Price(pricePerUnit, quantity, total));
    }
}
