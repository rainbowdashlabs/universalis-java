---
title: WebSocket client
sidebar_position: 3
---

# WebSocket client

`UniversalisWs` connects to the Universalis WebSocket (`wss://universalis.app/api/ws`) and calls your listeners when
listings or sales are added or removed. It is in the module `universalis-ws`.

```java
UniversalisWs ws = UniversalisWs.getDefault()
        .itemNameSupplier(Items.create())
        .subscribe(Subscriptions.listingAdd().restrict(Worlds.europe().light()))
        .subscribe(Subscriptions.salesAdd())
        .registerListener(new ListenerAdapter() {
            @Override
            public void onListingAdd(ListingAddEvent event) {
                System.out.println(event.listings().size() + " new listings of " + event.item().name().english());
            }
        })
        .build();
```

## Build the client

`UniversalisWs.getDefault()` returns a builder with a connection timeout of 10 seconds.
`UniversalisWs.websocketBuilder()` lets you set up the connection first: SSL (`setSSLContext`,
`setSSLSocketFactory`), timeouts in milliseconds (`setConnectionTimeout`, `setSocketTimeout`), IPv4 and IPv6
(`setDualStackMode`, `setDualStackFallbackDelay`), `setVerifyHostname` and the server names for SNI. Its `build()`
returns the same builder.

| Builder method | What it does |
|---|---|
| `subscribe(Subscription)` | Subscribes to a channel. See [Subscriptions](#subscriptions). |
| `registerListener(EventListener...)` | Adds listeners for the events. |
| `itemNameSupplier(NameSupplier)` | Adds names to the items. See [Item names](./items.md). The default adds none. |
| `websocketUrl(String)` | Connects to another server. |
| `eventThreadPool(ExecutorService)` | The executor that opens the connection. |
| `build()` | Starts connecting in the background and returns the client. |

`build()` returns before the connection is open. Call `awaitReady()` to wait until it is.

## Subscriptions

`Subscriptions` creates the channels:

| Method | Event |
|---|---|
| `listingAdd()` | Listings were added. |
| `listingRemove()` | Listings were removed. |
| `salesAdd()` | Items were sold. |
| `salesRemove()` | Deprecated. Sales were removed. |

A subscription without a restriction covers every world. Restrict it with `restrict(World)`, `restrict(DataCenter)`
or `forRegion(Region)`. You can call them more than once; the worlds add up. The client subscribes to one channel per
world.

```java
Subscription odinSales = Subscriptions.salesAdd().restrict(Worlds.europe().light().odin);
```

`subscribe(Subscription)` and `unsubscribe(Subscription)` on the client change subscriptions while it runs. While
the client is connected the change is sent right away. The client keeps the list and subscribes to it again after every
reconnect.

## Events

Implement `EventListener`, or extend `ListenerAdapter` and override only what you need.

| Method | Event | Holds |
|---|---|---|
| `onListingAdd` | `ListingAddEvent` | `item()`, `world()`, `listings()` |
| `onListingRemove` | `ListingRemoveEvent` | `item()`, `world()`, `listings()` |
| `onListingUpdate` | `ListingUpdateEvent` | `item()`, `world()`, `added()`, `removed()` |
| `onSalesAdd` | `SalesAddEvent` | `item()`, `world()`, `sales()` |
| `onSalesRemove` | `SalesRemoveEvent` (deprecated) | `item()`, `world()`, `sales()` |

Universalis has no update event. When someone uploads the market board, Universalis removes the old listings and adds
the new ones. The client keeps each remove event for 10 seconds. When an add event for the same item on the same world
follows, it calls `onListingUpdate` with the listings that were added and removed. The add and remove events are still
delivered on their own.

Listings and sales are the same types as in the [REST client](./rest/market-board.md#the-response).

Listeners run on the thread that reads from the WebSocket. Keep them short, or hand the work to your own executor, so
the client keeps up with new messages.

## Connection

When the connection drops, the client connects again after 5 seconds and subscribes again. A failed connection
attempt is retried every 5 seconds as well. `disconnect()` closes the connection and stops reconnecting.

`awaitReady()` blocks until the client is connected. `awaitReady(Duration)` waits at most the given time and returns
whether the client is connected.
