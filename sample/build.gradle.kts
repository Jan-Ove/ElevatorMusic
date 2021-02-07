plugins {
    id("hamburg.janove.elevator-music")
}

elevatorMusic {
    waitMusic = file("../../../Downloads/girl_from_ipanema.wav")
    successSound = file("../../../Downloads/bell.wav")
}

task("longRunningTask") {
    doLast {
        Thread.sleep(20000)
    }
}