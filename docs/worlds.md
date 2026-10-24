---
title: Worlds
sidebar_position: 4
---

# Worlds

`Worlds` in `universalis-core` holds the regions, data centers and worlds the library knows, so you do not need to
look up ids.

```java
World odin = Worlds.europe().light().odin;
DataCenter light = Worlds.europe().light();
Region europe = Worlds.europe();
```

| Region | Method | Data centers |
|---|---|---|
| Europe | `Worlds.europe()` | `chaos()`, `light()` |
| North America | `Worlds.northAmerica()` | `aether()`, `crystal()`, `primal()`, `dynamis()` |
| Oceania | `Worlds.oceania()` | `materia()` |
| Japan | `Worlds.japan()` | `elemental()`, and the deprecated `gaia()`, `mana()`, `meteor()` |
| China (中国) | `Worlds.china()` | `chocobo()` (陆行鸟), `moguli()` (莫古力), `fatCat()` (猫小胖), `doDoChai()` (豆豆柴) |
| Korea (한국) | `Worlds.korea()` | `koreaDc()` (한국) |
| Traditional Chinese (繁中服) | `Worlds.traditionalChinese()` | `luXingNiao()` (陸行鳥) |
| NA Cloud DC | `Worlds.naCloud()` | `naCloudDc()` (NA Cloud DC (Beta)) |

The worlds are fields of their data center, named like the world in lower case, for example
`Worlds.northAmerica().crystal().balmung`. Worlds with Chinese or Korean names have Latin field names, for example
`Worlds.korea().koreaDc().carbuncle`. `DataCenter.name()` and `Region.name()` return the names Universalis uses.

`World`, `DataCenter` and `Region` all have `worlds()`, so you can pass any of them where a list of worlds is needed.
A `World` also has `name()`, `id()` and `dataCenter()`. A `DataCenter` has `name()`, `id()` and `region()`.

## Look up by name or id

| Method | Returns |
|---|---|
| `Worlds.regions()` | Every region. |
| `Worlds.worldById(int)` | The world. For an unknown id, a world with that id, an empty name and no data center. |
| `Worlds.worldByName(String)` | The world, ignoring case. For an unknown name, a world with that name and the id `-1`. |
| `Worlds.datacenterByName(String)` | The data center, ignoring case, or `null`. |
| `Worlds.datacenterById(int)` | The data center, or `null`. |
| `Worlds.regionByName(String)` | The region, ignoring case, or `null`. |

Worlds in responses that the library does not know are built the same way and remembered for later lookups.

:::warning
Universalis no longer knows the Japanese data centers Gaia, Mana and Meteor and lists all Japanese worlds under
Elemental. Requests for `gaia()`, `mana()` or `meteor()` fail with an `ErrorResponseException`. Ask for a world, for
`elemental()` or for `Worlds.japan()` instead.
:::
