package dev.me.extension

import dev.me.extension.file.FileManager.initDir
import dev.me.extension.value.handler.ExampleValueHandler
import dev.me.extension.value.render.ExampleValueRenderer
import net.nonemc.leaf.command.Command.registerValueHandler
import net.nonemc.leaf.value.render.ValueRenderRegistry.register

@Suppress("UNUSED")
object Main {
    const val EXTENSION_NAME = "Example-Extension"
    const val EXTENSION_VERSION = "1.0.0"

    @JvmStatic
    fun init(){
        println("Init")
        //SoundManager.initSounds()
    }

    @JvmStatic
    fun initEnd(){
        initDir()
        registerValueHandler(ExampleValueHandler())
        register(ExampleValueRenderer())
    }
}
