plugins {
    kotlin("jvm") version "1.4.20"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("io.papermc.paper:paper-api:1.17.1-R0.1-SNAPSHOT")
}

val shade = configurations.create("shade")
shade.extendsFrom(configurations.implementation.get())

tasks {
    processResources {
        filesMatching("*.yml") {
            expand(project.properties)
        }
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "16"
    }

    create<Jar>("sourceJar") {
        archiveClassifier.set("source")
        from(sourceSets["main"].allSource)
    }

    jar {
        from (shade.map { if (it.isDirectory) it else zipTree(it) } )
    }
}
