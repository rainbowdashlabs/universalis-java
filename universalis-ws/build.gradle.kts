dependencies {
    api(project(":universalis-core"))

    // Websockets
    api(libs.websocketclient)
    api(libs.mongodb.bson)
    api(libs.google.guava)
}
