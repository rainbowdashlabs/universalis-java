plugins {
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

description = "A client for the Universalis WebSocket API."

dependencies {
    api(project(":universalis-core"))
    api(libs.websocket)
    api(libs.bson)
    api(libs.caffeine)
}
