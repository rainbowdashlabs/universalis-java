plugins {
    alias(libs.plugins.aggregate.javadoc)
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

description = "The Universalis REST and WebSocket clients. Its Javadoc covers all modules."

dependencies {
    api(project(":universalis-rest"))
    api(project(":universalis-ws"))

    javadoc(project(":universalis-core"))
    javadoc(project(":universalis-rest"))
    javadoc(project(":universalis-ws"))
}
