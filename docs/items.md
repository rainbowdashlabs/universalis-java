---
title: Item names
sidebar_position: 5
---

# Item names

Universalis only sends item ids. Responses and events hold an `Item` with the `id()` and a `name()`. By default the
name is empty. Give the client a `NameSupplier` to fill it.

## Items

`Items` loads the names of every item in English, German, French and Japanese from the
[FFXIV Teamcraft](https://github.com/ffxiv-teamcraft/ffxiv-teamcraft) data on GitHub (`Items.DEFAULT_SOURCE_URI`, the
`staging` branch):

```java
Items items = Items.create();

UniversalisRest rest = UniversalisRest.builder().setItemNameSupplier(items).build();
UniversalisWsBuilder ws = UniversalisWs.getDefault().itemNameSupplier(items);
```

`create()` downloads the file with a timeout of 30 seconds and throws an `IOException` when that fails. To load the
names from somewhere else, for example a copy you host yourself, pass the address: `Items.create(URI)`. The file must
have the same shape: a JSON object with the item id as key and `{"en", "de", "fr", "ja"}` as value.

Create it once and share it. It holds every item in memory.

## Look up items

`NameSupplier` also works on its own:

| Method | Returns |
|---|---|
| `fromId(int)` | The `Name` of an item, or an empty name for an unknown id. |
| `fromName(String)` | The id of an item by its name in any language, ignoring case. |
| `fromName(Language, String)` | The id by its name in one language, ignoring case. |

`Name` has `english()`, `german()`, `french()`, `japanese()` and `get(Language)`.

```java
Optional<Integer> id = items.fromName(itemName);
String german = items.fromId(itemId).german();
```

## Your own supplier

Implement `NameSupplier` to use your own data, for example a database. Provide `ids()` and one map per language from
the lower-case name to the id; the lookups above are built on them.
