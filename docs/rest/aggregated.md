---
title: Aggregated prices
sidebar_position: 3
---

# Aggregated prices

`aggregated()` asks for a summary of up to 100 items at once: the cheapest and the median listing, the most recent
sale, the average sale price and the units sold per day. Universalis builds it from cached values only, so prefer it
over the [market board](./market-board.md) when you do not need single listings and sales.

```java
AggregatedResponse response = rest.aggregated()
        .dataCenter(Worlds.europe().light())
        .itemsIds(5057, 5058)
        .complete();

for (AggregatedItem item : response) {
    ListingPrice cheapest = item.highQuality().minListing().dataCenter();
    System.out.println(item.item().id() + ": " + cheapest.price() + " gil on " + cheapest.world());
}
```

Pick one of `world`, `dataCenter` or `region` as for the [market board](./market-board.md#where), then 1 to 100 items
with `itemsIds` or `items`. No item or more than 100 throw an `IllegalArgumentException`.

## The response

`AggregatedResponse` is an `Iterable` of `AggregatedItem`. `failedItems()` lists the items Universalis has no data
for.

An `AggregatedItem` holds the `item()`, `worldUploadTimes()` (a `WorldUploadTime` with `world()` and `timestamp()`
per world) and the values split by quality: `normalQuality()` and `highQuality()`. Each is an `AggregatedValues` with:

| Field | Type | What it is |
|---|---|---|
| `minListing()` | `Scoped<ListingPrice>` | The cheapest listing: `price()` and `world()`. |
| `medianListing()` | `Scoped<ListingPrice>` | The median listing price. `world()` is `null`. |
| `recentPurchase()` | `Scoped<Purchase>` | The most recent sale: `price()`, `timestamp()` and `world()`. |
| `averageSalePrice()` | `Scoped<AveragePrice>` | The average `price()` of the sales of the last 4 days. |
| `dailySaleVelocity()` | `Scoped<SaleVelocity>` | The units sold per day (`quantity()`) over the last 4 days. |

A `Scoped` value has `world()`, `dataCenter()` and `region()`. Universalis fills the scopes the request covers: a
world request has all three, a data center request `dataCenter()` and `region()`, a region request only `region()`.
The others are `null`, and so is a whole field when Universalis has no data for it. In the world scope, `world()` of a
listing or sale is `null`: it is the world you asked for.
