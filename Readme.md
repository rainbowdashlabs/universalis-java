![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/rainbowdashlabs/universalis-java/verify.yml?style=for-the-badge&label=Build)
![GitHub Workflow Status](https://img.shields.io/github/actions/workflow/status/rainbowdashlabs/universalis-java/publish_to_nexus.yml?style=for-the-badge&label=Publish)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/maven-releases/de.chojo.universalis/universalis?label=Release&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_releases]
[![Sonatype Nexus (Development)](https://img.shields.io/nexus/maven-dev/de.chojo.universalis/universalis?label=DEV&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_dev]
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/de.chojo.universalis/universalis?color=orange&label=Snapshot&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_releases]

### [Javadocs](https://rainbowdashlabs.github.io/universalis-java/)

# Universalis-java

Allows to connect to universalis via websockets. Events can be filtered by world, datacenter and regions.

Additionally access to non-user routes is supported as well via a rest client.

## Gradle
```kts
repositories {
    maven("https://eldonexus.de/repository/maven-public")
}

dependencies {
    implementation("de.chojo.universalis", "universalis", "version")
}
```

If you only want the websocket or the rest client you can use `universalis-rest` or `universalis-ws`

## Websocket Example
```java
public class WebsocketExample {
    public static void main(String[] args) throws IOException {
        // Create a new default websocket
        UniversalisWs.getDefault()
                     // Subscribe to sales add channel of odin
                     .subscribe(Subscriptions.salesAdd().restrict(Worlds.europe().light().odin))
                     // Subscribe to listing add for all worlds on light and the one world on chaos
                     .subscribe(Subscriptions.listingAdd().restrict(Worlds.europe().light()).restrict(Worlds.europe().chaos().OMEGA))
                     // subcribe to the remove channel on all worlds
                     .subscribe(Subscriptions.listingRemove())
                     // Register listener to handle evens
                     .registerListener(new ListenerAdapter() {
                         @Override
                         public void onListingAdd(ListingAddEvent event) {
                             System.out.println("New listings of " + event.item() + " on world " + event.world());
                             for (var listing : event.listings()) {
                                 System.out.println(listing);
                             }
                         }

                         @Override
                         public void onSalesAdd(SalesAddEvent event) {
                             System.out.println("New sales of " + event.item() + " on world " + event.world());
                             for (var e : event.sales()) {
                                 System.out.println(e);
                             }
                         }

                         @Override
                         public void onListingUpdate(ListingUpdateEvent event) {
                             System.out.println("Listings updated of " + event.item() + " on world " + event.world());
                         }
                     })
                     .build();
    }
}
```

## Rest Example

The rest client provides methods for async (queue) and sync (complete) retrieval. All routes which do not require a 
user token are available via the api instance. The client will also take ratelimiting into account.

```java
public class RestExample {
    public static void main(String[] args) {
        // Create a rest client with default settings.
        UniversalisRest rest = UniversalisRest.builder().build();

        // retrieve valid worlds synchronours
        WorldsResponse worlds = rest.worlds().complete();
        for (World world : worlds) {
            System.out.printf("World %s exists%n", world.name());
        }
        // create a market board request.
        rest.marketBoard()
            // Restrict prices to the european datacenters
            .region(Worlds.europe())
            // Get data for only one item
            .itemsIds(36113)
            // only retrieve high quality prices
            .highQuality()
            // exclude taxes
            .noGst()
            // send the request async
            .queue()
            // handle the result
            .whenComplete((res, err) -> {
                System.out.println("Min hq price is" + res.minPrice().highQuality());
            });
    }
}
```

### Side notes

#### Iterating
All list like responses are iterable. When you have some kind of collection contained, you usually can directly use a 
foreach loop

#### Richer model
Not all models are exactly how universalis provides them. The websocket and rest client can both parse item ids to 
items and add localized names for them. The same works for worlds, which will always be represented by world object.

Stuff which usually is split into general, NQ and HQ data is combined into its own object, like you can see in the 
price example above.

[nexus_releases]: https://eldonexus.de/#browse/browse:maven-releases:de%2Fchojo%2Funiversalis%2Funiversalis
[nexus_snapshots]: https://eldonexus.de/#browse/browse:maven-snapshots:de%2Fchojo%2Funiversalis%2Funiversalis
[nexus_dev]: https://eldonexus.de/#browse/browse:maven-dev:de%2Fchojo%2Funiversalis%2Funiversalis
