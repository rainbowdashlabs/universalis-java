# Universalis Java

A Java client for [Universalis](https://universalis.app), the market board data of Final Fantasy XIV: a REST client
for prices, sale history and statistics, and a WebSocket client for new listings and sales as they happen.

[![Release](https://img.shields.io/nexus/maven-releases/de.chojo.universalis/universalis?label=Release&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][release]
[![Snapshot](https://img.shields.io/nexus/s/de.chojo.universalis/universalis?color=orange&label=Snapshot&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][snapshot]

## Requirements

Java 21 or newer.

## Dependency

```kotlin
repositories {
    maven("https://eldonexus.de/repository/maven-public/")
}

dependencies {
    implementation("de.chojo.universalis:universalis:2.1.0")
}
```

Use `universalis-rest` or `universalis-ws` instead of `universalis` for only one of the clients.

Documentation: https://docs.chojo.dev/universalis

## Licence

LGPL-3.0-or-later, see [LICENSE.md](LICENSE.md).

[release]: https://eldonexus.de/#browse/browse:maven-releases:de%2Fchojo%2Funiversalis%2Funiversalis
[snapshot]: https://eldonexus.de/#browse/browse:maven-snapshots:de%2Fchojo%2Funiversalis%2Funiversalis
