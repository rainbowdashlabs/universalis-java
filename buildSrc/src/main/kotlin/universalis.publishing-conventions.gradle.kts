plugins {
    `maven-publish`
    id("de.chojo.publishdata")
}

publishData {
    useEldoNexusRepos()
    publishComponent("java")
}

publishing {
    publications.create<MavenPublication>("maven") {
        publishData.configurePublication(this)
        pom {
            url = "https://github.com/rainbowdashlabs/universalis-java"
            developers {
                developer {
                    name = "Florian Fülling"
                    url = "https://github.com/rainbowdashlabs"
                }
            }
            licenses {
                license {
                    name = "GNU Affero General Public License v3.0"
                    url = "https://github.com/rainbowdashlabs/universalis-java/blob/main/LICENSE.md"
                }
            }
        }
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("NEXUS_USERNAME")
                    password = System.getenv("NEXUS_PASSWORD")
                }
            }

            setUrl(publishData.getRepository())
            name = "EldoNexus"
        }
    }
}
