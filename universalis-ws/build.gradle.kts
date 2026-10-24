plugins {
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

dependencies {
    api(project(":universalis-core"))
    api(libs.websocket)
    api(libs.bson)
    api(libs.caffeine)
}
