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
| North America | `Worlds.northAmerica()` | `aether()`, `crystal()`, `primal()` |
| Oceania | `Worlds.oceania()` | `materia()` |
| Japan | `Worlds.japan()` | `elemental()`, `gaia()`, `mana()`, `meteor()` |
| China | `Worlds.china()` | `chocobo()`, `moguli()`, `fatCat()`, `doDoChai()` |

The worlds are fields of their data center, named like the world in lower case, for example
`Worlds.northAmerica().crystal().balmung`.

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
The list is not complete. It lacks the North American data center Dynamis, the Korean and the Traditional Chinese
regions, and the NA Cloud DC. Universalis also no longer knows the Japanese data centers Gaia, Mana and Meteor:
requests for them fail with an `ErrorResponseException`.
:::
