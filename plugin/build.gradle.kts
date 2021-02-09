plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.12.0"
}

repositories {
    mavenCentral()
}

group = "hamburg.janove"
version = "0.1"

pluginBundle {
    website = "https://github.com/Jan-Ove/ElevatorMusic"
    vcsUrl = website
    tags = listOf("waiting", "music")
}

gradlePlugin {
    plugins {
        create("elevator") {
            id = "hamburg.janove.elevator-music"
            displayName = "Play music while you wait on your build"
            description = "Plugin that plays music while the build is running"
            implementationClass = "hamburg.janove.elevatormusic.ElevatorMusicPlugin"
        }
    }
}