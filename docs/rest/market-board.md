---
title: Market board
sidebar_position: 2
---

# Market board

`marketBoard()` asks for what the market board currently shows for an item: the listings, the most recent sales and
prices calculated from them.

```java
MarketBoardResponse response = rest.marketBoard()
        .world(Worlds.europe().light().odin)
        .itemsIds(5057)
        .listingsLimit(10)
        .complete();

for (Listing listing : response.listings()) {
    System.out.println(listing.price().pricePerUnit() + " gil from " + listing.retainer().name());
}
```

## Where

Pick exactly one place. A second call throws an `IllegalStateException`.

| Method | Data from |
|---|---|
| `world(World)` | One world. |
| `dataCenter(DataCenter)` | Every world of a data center. |
| `region(Region)` | Every world of a region. |

Get the objects from `Worlds`, for example `Worlds.europe().light().odin`. See [Worlds](../worlds.md).

## Which item

`itemsIds(Integer...)` and `itemsIds(Collection<Integer>)` take item ids. `items(Item...)` and
`items(Collection<Item>)` take `Item` objects, for example from an earlier response.

A request takes exactly one item. More than one item, or none, throws an `IllegalArgumentException`; setting the
item a second time throws an `IllegalStateException`. Universalis answers requests for several items with a different
response, which this version does not cover.

## Options

All options are optional.

| Method | Universalis parameter | Default | What it does |
|---|---|---|---|
| `listingsLimit(int)` | `listings` | all listings | How many listings to return. |
| `historyLimit(int)` | `entries` | 5 | How many recent sales to return. |
| `highQuality()` | `hq=true` | both qualities | Only high quality listings and sales. |
| `normalQuality()` | `hq=false` | both qualities | Only normal quality listings and sales. |
| `highQuality(boolean)` | `hq` | both qualities | `true` is `highQuality()`, `false` is `normalQuality()`. |
| `historyTime(Duration)` | `entriesWithin` | no limit | Only sales within this time before now. Sent in seconds. |
| `statsTime(Duration)` | `statsWithin` | 7 days | The time before now that prices and sale velocity are calculated over. Sent in milliseconds. |

`historyTime` and `statsTime` throw an `IllegalArgumentException` for a negative duration.

## The response

`MarketBoardResponse` holds:

| Field | Type | What it is |
|---|---|---|
| `item()` | `Item` | The item. |
| `world()`, `dataCenter()`, `region()` | `World`, `DataCenter`, `Region` | The place you asked for. The other two are `null`. |
| `lastUploadTime()` | `Instant` | When data for this item was last uploaded. |
| `listings()` | `List<Listing>` | The current listings. |
| `recentHistory()` | `List<Sale>` | The most recent sales. |
| `currentAveragePrice()` | `QualityIndicator<Float>` | Average price of the current listings. |
| `averagePrice()` | `QualityIndicator<Float>` | Average price of the sales in the stats time. |
| `minPrice()`, `maxPrice()` | `QualityIndicator<Integer>` | Lowest and highest price of the current listings. |
| `saleVelocity()` | `QualityIndicator<Float>` | Average sales per day in the stats time. |
| `stackSizeHistogram()` | `QualityIndicator<Map<Integer, Integer>>` | How many listings there are per stack size. |
| `worldUploadTimes()` | `Map<World, Instant>` | When data was last uploaded per world. Empty for a single world. |
| `listingsCount()`, `recentHistoryCount()` | `int` | How many listings and sales Universalis has, also beyond the limits you set. |
| `unitsForSale()`, `unitsSold()` | `int` | The units in all listings and in all sales. |
| `hasData()` | `boolean` | Whether Universalis has any data for the item. |

A `QualityIndicator` splits a value by quality: `general()` covers both, `normalQuality()` and `highQuality()` one
each.

A `Listing` holds the `price()` (`pricePerUnit`, `quantity`, `total`), the `retainer()` (`id`, `name`, `city`), the
`creator()` of crafted items, `meta()` (`hq`, `crafted`, `stainId`, `materia`), the `world()`, `tax()`,
`lastReviewTime()`, `listingId()`, `sellerId()` and `onMannequin()`.

A `Sale` holds `hq()`, the `price()`, the `timestamp()`, `onMannequin()`, the `world()` and the `buyerName()`.
