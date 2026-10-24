---
title: Other endpoints
sidebar_position: 4
---

# Other endpoints

## Worlds and data centers

```java
for (World world : rest.worlds().complete()) {
    System.out.println(world.id() + " " + world.name());
}

for (DataCenter dataCenter : rest.dataCenters().complete()) {
    System.out.println(dataCenter.name() + " in " + dataCenter.region());
}
```

Both return what Universalis knows right now. `worlds()` returns the `World` objects of [`Worlds`](../worlds.md) where
the library knows the world. `dataCenters()` returns plain records (`de.chojo.universalis.entities.DataCenter`) with
the `name()`, the `region()` name and the `worlds()`, not the `DataCenter` objects of `Worlds`.

## Marketable items

`marketable()` returns the items that can be sold on the market board:

```java
MarketableResponse marketable = rest.marketable().complete();
System.out.println(marketable.items().size() + " marketable items");
```

## Tax rates

`taxRates()` returns the market tax per city of a world, in percent:

```java
TaxRatesResponse taxes = rest.taxRates().world(Worlds.europe().light().odin).complete();
int limsa = taxes.rate(City.LIMSA_LOMINSA);
```

`TaxRatesResponse` is an `Iterable` of `Map.Entry<City, Integer>`.

## User lists

`list(String)` returns a list that someone created on universalis.app. The id is the last part of the list's address.

```java
UserListResponse list = rest.list(listId).complete();
System.out.println(list.name() + " has " + list.items().size() + " items, changed " + list.updated());
```

`UserListResponse` holds the `id()`, `name()`, `created()`, `updated()` and the `items()`, and is an `Iterable` of
`Item`. An unknown id fails with an `ErrorResponseException` (status 404).

## Statistics

`extra().stats()` holds the statistics requests.

| Method | Response | What it returns |
|---|---|---|
| `mostRecentlyUpdated()` | `MostRecentlyUpdatedResponse` | The items updated most recently. |
| `leastRecentlyUpdated()` | `LeastRecentlyUpdatedResponse` | The items not updated for the longest time. |
| `recentlyUpdated()` | `RecentlyUpdatedResponse` | Some recently updated items, without world or time. Universalis calls it a legacy endpoint. |
| `uploaderUploadCounts()` | `UploaderUploadCountResponse` | Uploads per uploading application. |
| `worldUploadCounts()` | `WorldUploadCountResponse` | Uploads per world, with their share of all uploads. |
| `uploadHistory()` | `UploadHistoryResponse` | Uploads per day for the last 30 days. |

`mostRecentlyUpdated()` and `leastRecentlyUpdated()` need a `world(World)` or a `dataCenter(DataCenter)`. `limit(int)`
sets how many items they return: 50 by default, at most 200. Higher values are lowered to 200.

```java
MostRecentlyUpdatedResponse recent = rest.extra().stats()
        .mostRecentlyUpdated()
        .dataCenter(Worlds.europe().light())
        .limit(20)
        .complete();

for (WorldItemRecently entry : recent) {
    System.out.println(entry.item().id() + " on " + entry.world());
}
```

## Content

`extra().content()` is deprecated. Universalis calls its content endpoint largely untested, and the response is only a
raw `Map<String, Object>`.
