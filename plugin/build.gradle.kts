plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
}

repositories {
    mavenCentral()
}

group = "hamburg.janove"
version = "0.1"

gradlePlugin {
    plugins {
        create("elevator") {
            id = "hamburg.janove.elevator-music"
            implementationClass = "hamburg.janove.elevatormusic.ElevatorMusicPlugin"
        }
    }
}