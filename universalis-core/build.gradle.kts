plugins {
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

description = "Models, worlds and item names shared by the Universalis clients."

dependencies {
    api(libs.jackson.databind)
    api(libs.jackson.annotations)
    api(libs.slf4j.api)
    api(libs.jetbrains.annotations)
}
