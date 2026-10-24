plugins {
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

description = "A client for the Universalis REST API."

dependencies {
    api(project(":universalis-core"))
    api(libs.bucket4j)
    api(libs.httpclient)

    testImplementation(libs.junit.pioneer)
}
