plugins {
    alias(libs.plugins.aggregate.javadoc)
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

group = "de.chojo.universalis"
version = "2.0.2"

subprojects {
    group = rootProject.group
    version = rootProject.version
}

dependencies {
    api(project(":universalis-rest"))
    api(project(":universalis-ws"))

    javadoc(project(":universalis-core"))
    javadoc(project(":universalis-rest"))
    javadoc(project(":universalis-ws"))
}
