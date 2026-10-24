---
title: Sale history
sidebar_position: 3
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
`region`, then the item with `itemsIds` or `items`.

:::warning
Ask for one item per request. With more than one item the response has no item and no sales.
:::

## Options

| Method | Universalis parameter | Default | What it does |
|---|---|---|---|
| `limit(int)` | `entriesToReturn` | 1800 | How many sales to return, at most 99999. |
| `historyTime(Duration)` | `entriesWithin` | 7 days | Only sales within this time before now. Sent in seconds. |
| `statsTime(Duration)` | `statsWithin` | 7 days | The time the sale velocity is calculated over. Sent in milliseconds. |

`historyTime` and `statsTime` throw an `IllegalArgumentException` for a negative duration.

## The response

`HistoryResponse` holds the `item()`, the place you asked for (`world()`, `dataCenter()`, `region()`),
`lastUploadTime()`, the `sales()`, the `saleVelocity()` and the `stackSizeHistogram()` (both as
[`QualityIndicator`](./market-board.md#the-response)).

A `MinimizedSale` holds `hq()`, the `price()` (`pricePerUnit`, `quantity`, and `total()` calculated from both), the
`timestamp()`, `onMannequin()`, the `world()` and the `buyerName()`.
