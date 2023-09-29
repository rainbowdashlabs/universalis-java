import com.diffplug.gradle.spotless.SpotlessPlugin

plugins {
    java
    `maven-publish`
    `java-library`
    id("de.chojo.publishdata") version "1.2.5"
    id("com.diffplug.spotless") version "6.22.0"
}

group = "de.chojo.universalis"
version = "1.3.1"

repositories {
    maven("https://eldonexus.de/repository/maven-public")
    maven("https://eldonexus.de/repository/maven-proxies")
}

dependencies {
    api(project(":universalis-rest"))
    api(project(":universalis-ws"))
}

subprojects {
    apply {
        // We want to apply several plugins to subprojects
        plugin<JavaPlugin>()
        plugin<de.chojo.PublishData>()
        plugin<JavaLibraryPlugin>()
        plugin<MavenPublishPlugin>()
        plugin<SpotlessPlugin>()
    }
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://eldonexus.de/repository/maven-public/")
        maven("https://eldonexus.de/repository/maven-proxies/")
    }

    spotless {
        java {
            licenseHeaderFile(rootProject.file("HEADER.txt"))
            target("**/*.java")
        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(16))
        }
    }

    dependencies {
        testImplementation("org.junit.jupiter", "junit-jupiter-api", "5.10.0")
        testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", "5.10.0")
        testImplementation("org.mockito", "mockito-core", "3.+")
    }

    publishData {
        useEldoNexusRepos()
        publishComponent("java")
    }

    if (!project.name.contains("example")) {
        publishing {
            publications.create<MavenPublication>("maven") {
                publishData.configurePublication(this)
                pom {
                    url.set("https://github.com/rainbowdashlabs/universalis-java")
                    developers {
                        developer {
                            name.set("Florian Fülling")
                            url.set("https://github.com/rainbowdashlabs")
                        }
                    }
                    licenses {
                        license {
                            name.set("GNU Affero General Public License v3.0")
                            url.set("https://github.com/rainbowdashlabs/universalis-java/blob/main/LICENSE.md")
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
    }

    // We configure some general tasks for our modules
    tasks {
        test {
            useJUnitPlatform()
            testLogging {
                events("passed", "skipped", "failed")
            }
        }

        javadoc {
            applyJavaDocOptions(options)
        }

        compileJava {
            options.encoding = "UTF-8"
        }

        javadoc {
            options.encoding = "UTF-8"
        }
    }
}

fun applyJavaDocOptions(options: MinimalJavadocOptions) {
    val javaDocOptions = options as StandardJavadocDocletOptions
    javaDocOptions.links(
        "https://javadoc.io/doc/com.google.code.findbugs/jsr305/latest/",
        "https://javadoc.io/doc/org.jetbrains/annotations/latest/",
        "https://docs.oracle.com/en/java/javase/${java.toolchain.languageVersion.get().asInt()}/docs/api/"
    )
}

tasks {
    register<Javadoc>("alljavadoc") {
        applyJavaDocOptions(options)

        setDestinationDir(file("${buildDir}/docs/javadoc"))
        val projects = project.rootProject.allprojects.filter { p -> !p.name.contains("example") }
        setSource(projects.map { p -> p.sourceSets.main.get().allJava })
        classpath = files(projects.map { p -> p.sourceSets.main.get().compileClasspath })
    }
}
