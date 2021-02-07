package hamburg.janove.elevatormusic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.util.concurrent.TimeUnit
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip

class ElevatorMusicPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val elevatorExtension = target.extensions.create<ElevatorPluginExtension>("elevatorMusic")
        target.afterEvaluate {
            if (elevatorExtension.waitMusic == null) {
                val slf4jLogger = LoggerFactory.getLogger("elevatorMusic")
                slf4jLogger.warn("Elevator music can not be played. Music file is missing.")
            } else {
                playMusic(elevatorExtension, target)
            }
        }
    }

    private fun playMusic(
        elevatorExtension: ElevatorPluginExtension,
        target: Project
    ) {
        val streams = mutableListOf<Closeable>()
        val clip = AudioSystem.getClip()
        val audioInputStream = AudioSystem.getAudioInputStream(elevatorExtension.waitMusic)
        streams.add(audioInputStream)
        clip.open(audioInputStream)
        clip.loop(-1)
        val successClip: Clip? = prepareSuccessClip(elevatorExtension, streams)
        target.gradle.buildFinished {
            clip.stop()
            successClip?.start()
            clip.close()
            successClip?.let {
                Thread.sleep(TimeUnit.MICROSECONDS.toMillis(it.microsecondLength))
                it.close()
            }
            streams.forEach(Closeable::close)
        }
    }

    private fun prepareSuccessClip(
        elevatorExtension: ElevatorPluginExtension,
        streams: MutableList<Closeable>
    ): Clip? {
        var successClip : Clip? = null
        elevatorExtension.successSound?.let {
            val successInputStream = AudioSystem.getAudioInputStream(elevatorExtension.successSound)
            streams.add(successInputStream)
            successClip = AudioSystem.getClip()
            successClip?.open(successInputStream)
        }
        return successClip
    }
}

