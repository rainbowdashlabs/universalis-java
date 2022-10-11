![GitHub Workflow Status](https://img.shields.io/github/workflow/status/RainbowDashLabs/universalis-java/Verify%20state?style=for-the-badge&label=Build)
![GitHub Workflow Status](https://img.shields.io/github/workflow/status/RainbowDashLabs/universalis-java/Publish%20to%20Nexus?style=for-the-badge&label=Publish)
[![Sonatype Nexus (Releases)](https://img.shields.io/nexus/maven-releases/de.chojo/universalis?label=Release&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_releases]
[![Sonatype Nexus (Development)](https://img.shields.io/nexus/maven-dev/de.chojo/universalis?label=DEV&logo=Release&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_dev]
[![Sonatype Nexus (Snapshots)](https://img.shields.io/nexus/s/de.chojou/universalis?color=orange&label=Snapshot&server=https%3A%2F%2Feldonexus.de&style=for-the-badge)][nexus_releases]

### [Javadocs](https://rainbowdashlabs.github.io/universalis-java/)

# Universalis-java

Allows to connect to universalis via websockets. Events can be filtered by world, datacenter and regions.

Support for the rest api itself is coming soon™

### Gradle
```kts
repositories {
    maven("https://eldonexus.de/repository/maven-public")
}

dependencies {
    implementation("de.chojo", "universalis", "version")
}
```

### Example
```java
public class MyUniversalisApp {
    public static void main(String[] args) {
        Universalis.getDefault()
                   .subscribe(Subscribtions.salesAdd().forWorld(Worlds.europe().light().ODIN))
                   .subscribe(Subscribtions.listingAdd())
                   .subscribe(Subscribtions.salesRemove())
                   .subscribe(Subscribtions.listingRemove())
                   .registerListener(new ListenerAdapter() {
                       @Override
                       public void onListingAdd(ListingAdd event) {
                           System.out.println("New listings of " + event.item() + " on world " + event.world());
                           for (var listing : event.listings()) {
                               System.out.println(listing);
                           }
                       }
        
                       @Override
                       public void onSalesAdd(SalesAdd event) {
                           System.out.println("New sales of " + event.item() + " on world " + event.world());
                           for (var e : event.sales()) {
                               System.out.println(e);
                           }
                       }
                   })
                   .build();
    }
}
```

[nexus_releases]: https://eldonexus.de/#browse/browse:maven-releases:de%2Fchojo%2Funiversalis
[nexus_snapshots]: https://eldonexus.de/#browse/browse:maven-snapshots:de%2Fchojo%2Funiversalis
[nexus_dev]: https://eldonexus.de/#browse/browse:maven-dev:de%2Fchojo%2Funiversalis
