rootProject.name = "universalis"

include("universalis-ws")
include("universalis-rest")
include("universalis-core")
include("universalis-example")

pluginManagement{
    repositories{
        mavenLocal()
        gradlePluginPortal()
        maven{
            name = "EldoNexus"
            url = uri("https://eldonexus.de/repository/maven-public/")

        }
    }
}
