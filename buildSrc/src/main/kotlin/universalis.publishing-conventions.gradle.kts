plugins {
    `maven-publish`
    id("de.chojo.publishdata")
}

publishData {
    eldoNexus(dev = false)
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        pom {
            url = "https://github.com/rainbowdashlabs/universalis-java"
            inceptionYear = "2022"
            licenses {
                license {
                    name = "LGPL-3.0-or-later"
                    url = "https://www.gnu.org/licenses/lgpl-3.0.html"
                }
            }
            developers {
                developer {
                    id = "rainbowdashlabs"
                    name = "Nora Fülling"
                    email = "mail@chojo.dev"
                    url = "https://github.com/rainbowdashlabs"
                }
            }
            scm {
                url = "https://github.com/rainbowdashlabs/universalis-java"
                connection = "scm:git:git://github.com/rainbowdashlabs/universalis-java.git"
                developerConnection = "scm:git:ssh://github.com/rainbowdashlabs/universalis-java.git"
            }
        }
    }
}
