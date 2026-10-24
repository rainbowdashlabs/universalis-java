---
title: Sale history
sidebar_position: 4
---

# Sale history

`history()` asks for the sales of an item. It returns more sales than the market board request and fewer details per
sale.

```java
HistoryResponse response = rest.history()
        .dataCenter(Worlds.europe().light())
        .itemsIds(5057)
        .historyTime(Duration.ofDays(1))
        .complete();

for (MinimizedSale sale : response.sales()) {
    System.out.println(sale.price().quantity() + "x for " + sale.price().pricePerUnit() + " gil on " + sale.world());
}
```

Where and which item work as for the [market board](./market-board.md#where): pick one of `world`, `dataCenter` or
`region`, then exactly one item with `itemsIds` or `items`.

## Options

| Method | Universalis parameter | Default | What it does |
|---|---|---|---|
| `limit(int)` | `entriesToReturn` | 1800 | How many sales to return, at most 99999. |
| `historyTime(Duration)` | `entriesWithin` | 7 days | Only sales within this time before now, or before `entriesUntil`. Sent in seconds. |
| `statsTime(Duration)` | `statsWithin` | 7 days | The time the sale velocity is calculated over. Sent in milliseconds. |
| `entriesUntil(Instant)` | `entriesUntil` | now | Only sales up to this time. `historyTime` then counts back from here. Sent in seconds. |
| `minSalePrice(int)` | `minSalePrice` | none | Only sales with at least this price per unit. |
| `maxSalePrice(int)` | `maxSalePrice` | none | Only sales with at most this price per unit. |

`historyTime` and `statsTime` throw an `IllegalArgumentException` for a negative duration.

## The response

`HistoryResponse` holds the `item()`, the place you asked for (`world()`, `dataCenter()`, `region()`),
`lastUploadTime()`, the `sales()`, the `saleVelocity()` and the `stackSizeHistogram()` (both as
[`QualityIndicator`](./market-board.md#the-response)).

A `MinimizedSale` holds `hq()`, the `price()` (`pricePerUnit`, `quantity`, and `total()` calculated from both), the
`timestamp()`, `onMannequin()`, the `world()` and the `buyerName()`.
