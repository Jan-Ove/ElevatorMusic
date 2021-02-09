# Elevator Music

Gradle is an awesome build tool with good performance, but every time it gets faster, we tend to increase its workload.
So sometimes we might have to wait a little longer for the build.

This plugin adds elevator music to the build to make waiting more fun. 
It also adds the ability to set a success sound to play when the build is finished, so you know when you should resume working.

![https://xkcd.com/303/](https://imgs.xkcd.com/comics/compiling.png)

## Installation
Add the plugin to the `plugins`-block and configure the 
music you want to hear. Currently, supported file formats
are: WAV, AIFF and AU.
```kotlin
plugins {
  id("hamburg.janove.elevator-music") version "0.1"
}

elevatorMusic {
    waitMusic = file("../girl_from_ipanema.wav")
    successSound = file("../bell.wav")
}
```
