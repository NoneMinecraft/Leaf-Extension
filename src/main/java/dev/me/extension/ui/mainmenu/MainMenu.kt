package dev.me.extension.ui.mainmenu

import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.gui.GuiYesNoCallback
import net.minecraft.client.gui.GuiSelectWorld
import net.minecraft.client.gui.GuiMultiplayer
import net.minecraft.client.gui.GuiOptions
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.GameSettings
import java.awt.Color

class MainMenu : GuiScreen(), GuiYesNoCallback {

    private val mcInstance: Minecraft = Minecraft.getMinecraft()
    private val gameSettings: GameSettings = mcInstance.gameSettings

    private val baseX = width / 2 + 1
    private val baseY = height / 2 + 70
    private val spacing = 25

    override fun initGui() {
        buttonList.clear()

        val singleButton = GuiButton(0, baseX, baseY + 0 * spacing, 70, 20, "SinglePlayer")
        val multiButton = GuiButton(1, baseX, baseY + 1 * spacing, 70, 20, "MultiPlayer")
        val optionsButton = GuiButton(2, baseX, baseY + 2 * spacing, 70, 20, "Options")
        val quitButton = GuiButton(3, baseX, baseY + 3 * spacing, 70, 20, "QuitGame")

        buttonList.addAll(listOf(singleButton, multiButton, optionsButton, quitButton))
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawBackground(0)

        for (button in buttonList) {
            drawModernButton(button, mouseX, mouseY)
        }

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    private fun drawModernButton(button: GuiButton, mouseX: Int, mouseY: Int) {
        val hovered = button.isMouseOver
        val color = if (hovered) Color(255, 255, 255, 200) else Color(255, 255, 255, 150)
        drawRect(button.xPosition, button.yPosition, button.xPosition + button.width, button.yPosition + button.height, color.rgb)
        drawCenteredString(mc.fontRendererObj, button.displayString, button.xPosition + button.width / 2, button.yPosition + 6, Color.BLACK.rgb)
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            0 -> openSinglePlayerScreen()
            1 -> openMultiPlayerScreen()
            2 -> openOptionsScreen()
            3 -> quitGame()
        }
    }

    private fun openSinglePlayerScreen() {
        try {
            val gui = GuiSelectWorld(this)
            mcInstance.displayGuiScreen(gui)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openMultiPlayerScreen() {
        try {
            val gui = GuiMultiplayer(this)
            mcInstance.displayGuiScreen(gui)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun openOptionsScreen() {
        try {
            val gui = GuiOptions(this, gameSettings)
            mcInstance.displayGuiScreen(gui)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun quitGame() {
        try {
            mcInstance.shutdown()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}