/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) Rainbowdashlabs and Contributor
 */

package de.chojo.universalis.entities.views;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.chojo.universalis.deserializer.SecondsDateTimeConverter;
import de.chojo.universalis.entities.City;
import de.chojo.universalis.entities.Creator;
import de.chojo.universalis.entities.ItemMeta;
import de.chojo.universalis.entities.Listing;
import de.chojo.universalis.entities.Price;
import de.chojo.universalis.entities.Retainer;
import de.chojo.universalis.worlds.World;
import de.chojo.universalis.worlds.Worlds;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://docs.universalis.app/#schema-listingview">See on Universalis</a>
 *
 * @param lastReviewTime The time that this listing was posted, in seconds since the UNIX epoch.
 * @param pricePerUnit   The price per unit sold.
 * @param quantity       The stack size sold.
 *                       //TODO: Add some richer mapping for stains
 * @param stainId        The ID of the dye on this item.
 * @param worldName      The world name, if applicable.
 * @param worldId        The world ID, if applicable.
 * @param creatorName    The creator's character name.
 * @param creatorId      A SHA256 hash of the creator's ID.
 * @param hq             Whether the item is high-quality.
 * @param isCrafted      Whether the item is crafted.
 * @param listingId      A SHA256 hash of the ID of this listing. Due to some current client-side bugs, this will almost always be null.
 * @param materia        The materia on this item.
 * @param onManequin     Whether the item is being sold on a mannequin.
 * @param retainerCity   The city ID of the retainer.
 * @param retainerId     A SHA256 hash of the retainer's ID.
 * @param retainerName   The retainer's name.
 * @param sellerId       A SHA256 hash of the seller's ID.
 * @param total          The total price.
 */
public record ListingView(
        @JsonProperty("lastReviewTime") @JsonDeserialize(converter = SecondsDateTimeConverter.class) LocalDateTime lastReviewTime,
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
        @JsonProperty("retainerCity") City retainerCity,
        @JsonProperty("retainerID") String retainerId,
        @JsonProperty("retainerName") String retainerName,
        @JsonProperty("sellerID") String sellerId,
        @JsonProperty("total") int total) {
    /**
     * Converts the {@link ListingView} to a {@link Listing} object.
     *
     * @return list of sales
     */
    public Listing toListing() {
        return toListing(Worlds.worldById(worldId));
    }

    /**
     * Converts the {@link ListingView} to a {@link Listing} object.
     *
     * @param world world for the sale
     * @return list of sales
     */
    public Listing toListing(World world) {
        return new Listing(lastReviewTime,
                world,
                new Creator(creatorName, creatorId),
                new ItemMeta(hq, isCrafted, stainId, materia),
                listingId,
                onManequin,
                new Retainer(retainerId, retainerName, retainerCity),
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
        result = 31 * result + retainerCity.ordinal();
        result = 31 * result + (retainerId != null ? retainerId.hashCode() : 0);
        result = 31 * result + (retainerName != null ? retainerName.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + total;
        return result;
    }
}
