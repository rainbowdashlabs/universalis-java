# Universalis-api

Allows to connect to universalis via websockets. Events can be filtered by world, datacenter and regions.

Minimal working example
```java
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
```
