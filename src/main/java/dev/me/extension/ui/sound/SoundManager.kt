package dev.me.extension.ui.sound

import net.nonemc.leaf.file.soundsDir
import net.nonemc.leaf.libs.file.FileLib
import java.io.File

object SoundManager {
    private val exampleSoundFile = File(soundsDir, "example.wav")

    var exampleSound: File

    init {
        initSounds()
        exampleSound = exampleSoundFile
    }

    fun initSounds() {
        if (!exampleSoundFile.exists()) {
            FileLib.unpackFile(exampleSoundFile, "assets/minecraft/leaf/sound/example.wav")
        }

    }

    /*
      Sound usage method:
      playSound(exampleSound)
    */
}