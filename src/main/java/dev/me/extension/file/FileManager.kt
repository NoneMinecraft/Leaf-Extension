package dev.me.extension.file

import dev.me.extension.Main.EXTENSION_NAME
import net.nonemc.leaf.file.extensionDir
import java.io.File

object FileManager {
    val mainDir = File(extensionDir,EXTENSION_NAME)

    fun initDir() {
        if (!mainDir.exists()) {
            mainDir.mkdirs()
        }
    }
}