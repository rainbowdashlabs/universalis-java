rootProject.name = "universalis"

include("universalis-ws")
include("universalis-rest")
include("universalis-core")
include("universalis-example")

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven {
            name = "EldoNexus"
            url = uri("https://eldonexus.de/repository/maven-public/")

        }
    }
}

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            plugin("spotless", "com.diffplug.spotless").version("6.25.0")

            version("jackson", "2.17.0")
            library("jackson-core", "com.fasterxml.jackson.core", "jackson-core").versionRef("jackson")
            library("jackson-databind", "com.fasterxml.jackson.core", "jackson-databind").versionRef("jackson")
            library("jackson-annotations", "com.fasterxml.jackson.core", "jackson-annotations").versionRef("jackson")
            library("jackson-yaml", "com.fasterxml.jackson.dataformat", "jackson-dataformat-yaml").versionRef("jackson")
            bundle("jackson-full", listOf("jackson-core", "jackson-databind", "jackson-annotations", "jackson-yaml"))
            bundle("jackson-lite", listOf("jackson-core", "jackson-databind", "jackson-annotations"))

            library("jetbrains-annotations", "org.jetbrains:annotations:24.1.0")

            version("slf4j", "2.0.12")
            library("slf4j-api", "org.slf4j", "slf4j-api").versionRef("slf4j")

            version("log4j", "2.23.1")
            // "org.apache.logging.log4j", "log4j-core", "2.23.1"
            library("apache-log4j-core", "org.apache.logging.log4j", "log4j-core").versionRef("log4j")
            library("apache-log4j-slf4j", "org.apache.logging.log4j", "log4j-slf4j2-impl").versionRef("log4j")
            bundle("log4j", listOf("apache-log4j-core", "apache-log4j-slf4j"))


            library("bucket4j", "com.bucket4j:bucket4j-core:8.10.1")
            library("apache-httpclient", "org.apache.httpcomponents.client5:httpclient5:5.3.1")

            library("websocketclient", "com.neovisionaries:nv-websocket-client:2.14")
            library("mongodb-bson", "org.mongodb:bson:5.0.1")
            library("google-guava", "com.google.guava:guava:33.1.0-jre")

        }

        create("testlibs") {
            version("junit", "5.10.2")
            library("junit-jupiter", "org.junit.jupiter", "junit-jupiter").versionRef("junit")
            library("junit-params", "org.junit.jupiter", "junit-jupiter-params").versionRef("junit")
            bundle("junit", listOf("junit-jupiter", "junit-params"))

            version("mockito", "5.+")
            library("mockito-core", "org.mockito", "mockito-core").versionRef("mockito")

            version("slf4j", "2.0.12")
            library("slf4j-noop", "org.slf4j", "slf4j-nop").versionRef("slf4j")
        }
    }
}
