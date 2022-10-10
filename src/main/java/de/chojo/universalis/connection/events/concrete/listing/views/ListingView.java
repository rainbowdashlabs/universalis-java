/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.universalis.connection.events.concrete.listing.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.chojo.universalis.entities.shared.City;
import de.chojo.universalis.entities.shared.Creator;
import de.chojo.universalis.entities.shared.ItemMeta;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.shared.Price;
import de.chojo.universalis.entities.shared.Retainer;
import de.chojo.universalis.entities.shared.World;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListingView that)) return false;

        if (pricePerUnit != that.pricePerUnit) return false;
        if (quantity != that.quantity) return false;
        if (stainId != that.stainId) return false;
        if (hq != that.hq) return false;
        if (isCrafted() != that.isCrafted()) return false;
        if (onManequin != that.onManequin) return false;
        if (retainerCity != that.retainerCity) return false;
        if (total != that.total) return false;
        if (!Objects.equals(creatorId, that.creatorId)) return false;
        if (!Objects.equals(materia, that.materia)) return false;
        if (!Objects.equals(retainerId, that.retainerId)) return false;
        if (!Objects.equals(retainerName, that.retainerName)) return false;
        return Objects.equals(sellerId, that.sellerId);
    }

    @Override
    public int hashCode() {
        int result = pricePerUnit;
        result = 31 * result + quantity;
        result = 31 * result + stainId;
        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
        result = 31 * result + (hq ? 1 : 0);
        result = 31 * result + (isCrafted() ? 1 : 0);
        result = 31 * result + (materia != null ? materia.hashCode() : 0);
        result = 31 * result + (onManequin ? 1 : 0);
        result = 31 * result + retainerCity;
        result = 31 * result + (retainerId != null ? retainerId.hashCode() : 0);
        result = 31 * result + (retainerName != null ? retainerName.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }
}
