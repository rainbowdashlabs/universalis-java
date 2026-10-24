plugins {
    id("universalis.java-conventions")
    id("universalis.publishing-conventions")
}

dependencies {
    api(project(":universalis-core"))
    api(libs.bucket4j)
    api(libs.httpclient)

    testImplementation(libs.junit.pioneer)
}
