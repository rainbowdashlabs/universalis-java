---
title: API coverage
sidebar_position: 6
---

# API coverage

What the client wraps of the [Universalis API](https://docs.universalis.app), version 2.

## REST

| Universalis endpoint | Client | Notes |
|---|---|---|
| `/{worldDcRegion}/{itemIds}` | `marketBoard()` | One item per request. The `fields` parameter is not supported. |
| `/history/{worldDcRegion}/{itemIds}` | `history()` | One item per request. `entriesUntil`, `minSalePrice` and `maxSalePrice` are not supported. |
| `/aggregated/{worldDcRegion}/{itemIds}` | none | |
| `/worlds` | `worlds()` | |
| `/data-centers` | `dataCenters()` | |
| `/marketable` | `marketable()` | |
| `/tax-rates` | `taxRates()` | |
| `/extra/stats/most-recently-updated` | `extra().stats().mostRecentlyUpdated()` | |
| `/extra/stats/least-recently-updated` | `extra().stats().leastRecentlyUpdated()` | |
| `/extra/stats/recently-updated` | none | Legacy endpoint. |
| `/extra/stats/uploader-upload-counts` | `extra().stats().uploaderUploadCounts()` | |
| `/extra/stats/world-upload-counts` | `extra().stats().worldUploadCounts()` | |
| `/extra/stats/upload-history` | `extra().stats().uploadHistory()` | |
| `/extra/content/{contentId}` | `extra().content()` | Deprecated. |
| `/lists/{listId}` | none | |

## WebSocket

| Channel | Client |
|---|---|
| `listings/add` | `Subscriptions.listingAdd()` |
| `listings/remove` | `Subscriptions.listingRemove()` |
| `sales/add` | `Subscriptions.salesAdd()` |
| `sales/remove` | `Subscriptions.salesRemove()`, deprecated |

The world filter (`{world=<id>}`) is set with `restrict` and `forRegion`. See [WebSocket client](./websocket.md).
