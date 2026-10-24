---
title: The REST client
sidebar_position: 1
---

# The REST client

`UniversalisRest` sends requests to the Universalis REST API (`https://universalis.app/api/v2`). It is in the module
`universalis-rest`.

## Create a client

The quickest way is the default client:

```java
UniversalisRest rest = UniversalisRest.defaultApi();
```

Use the builder to change the defaults:

```java
UniversalisRest rest = UniversalisRest.builder()
        .setItemNameSupplier(Items.create())
        .setExecutorService(Executors.newScheduledThreadPool(4))
        .build();
```

| Builder method | Default | What it does |
|---|---|---|
| `setHttp(HttpClient)` | a new `HttpClient` with HTTP/2 | The client that sends the requests. |
| `setExecutorService(ScheduledExecutorService)` | 2 daemon threads named `universalis-rest-<n>` | Runs requests sent with `queue()`. |
| `setItemNameSupplier(NameSupplier)` | `NameSupplier.EMPTY` | Adds names to the items in responses. See [Item names](../items.md). |
| `setBaseUri(URI)` | `https://universalis.app/api/v2` | Sends the requests to another server, for example a test server. |

Create one client and reuse it. Every client has its own rate limit.

## Send a request

Every method of the client starts a request. Configure it, then send it:

- `complete()` sends the request and waits for the response.
- `queue()` sends it on the executor and returns a `CompletableFuture`.

```java
WorldsResponse worlds = rest.worlds().complete();

rest.worlds().queue().thenAccept(response -> {
    for (World world : response) {
        System.out.println(world.name());
    }
});
```

Responses that hold a list are `Iterable`, so you can loop over them directly.

| Method | Request | Page |
|---|---|---|
| `marketBoard()` | Listings, recent sales and prices of an item | [Market board](./market-board.md) |
| `aggregated()` | Price summary of up to 100 items | [Aggregated prices](./aggregated.md) |
| `history()` | Sale history of an item | [Sale history](./history.md) |
| `worlds()`, `dataCenters()`, `marketable()`, `taxRates()` | Game data and tax rates | [Other endpoints](./other-endpoints.md) |
| `list(String)` | A list a user created | [Other endpoints](./other-endpoints.md#user-lists) |
| `extra().stats()` | Upload statistics | [Other endpoints](./other-endpoints.md#statistics) |

## Rate limit

Universalis allows 25 requests per second with bursts of 50. The client keeps to it on its own: it can send 50
requests at once and gets 50 more every 2 seconds. A request that would break the limit waits until it may be sent.
`complete()` blocks while it waits. `queue()` waits without blocking a thread.

## Errors

The request methods throw unchecked exceptions from `de.chojo.universalis.exceptions`:

| Exception | When |
|---|---|
| `ErrorResponseException` | Universalis answered with a status of 400 or higher. `getMessage()` holds the response body. |
| `ResponseException` | The response could not be read or mapped. `ErrorResponseException` extends it. |
| `RequestException` | The request could not be built, or the thread was interrupted while waiting for the rate limit. |

With `queue()` the future completes exceptionally with the same exceptions, wrapped in a `CompletionException`.

## Close the client

`UniversalisRest` is `AutoCloseable`. `close()` shuts down the executor and the `HttpClient`, also those you passed to
the builder. Requests that are already running finish. Requests that have not started yet fail, and the client can not
send new requests. Do not close a client while other code still uses its executor or `HttpClient`.
