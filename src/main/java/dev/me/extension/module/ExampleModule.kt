package dev.me.extension.module

import dev.me.extension.event.ExampleEvent
import dev.me.extension.value.ExampleValue
import net.nonemc.leaf.event.*
import net.nonemc.leaf.font.Fonts
import net.nonemc.leaf.libs.log.normalLog
import net.nonemc.leaf.module.Module
import net.nonemc.leaf.module.ModuleCategory
import net.nonemc.leaf.module.ModuleInfo
import net.nonemc.leaf.value.*

@ModuleInfo(name = "ExampleModule", category = ModuleCategory.COMBAT)
object ExampleModule : Module() {

    //name, value, min, max
    val exampleValue = ExampleValue("ExampleValue", 5.0, 0.0, 10.0).displayable { true }

    //name, value, min, max
    val intValue = IntegerValue("IntValue", 5, 0, 10).displayable { true }

    //name, value, min, max
    private val floatValue = FloatValue("FloatValue", 5f, 0f, 10f).displayable { true }

    //name, value
    private val boolValue = BoolValue("BoolValue", true).displayable { true }

    //name, value
    private val textValue = TextValue("TextValue", "Message").displayable { true }

    //name, array, value
    private val listValue = ListValue("ListValue", arrayOf("type1", "type2"), "type1").displayable { true }

    //name, blockID
    private val blockValue = BlockValue("BlockValue", 100).displayable { true }

    //name, fonts
    private val fontValue = FontValue("FontValue", Fonts.font35).displayable { true }

    /*
     Value - Usage method:
     value.get()
    */

    override fun onEnable() {
        normalLog("onEnable")
    }

    override fun onDisable() {
        normalLog("onDisable")
    }

    @EventTarget
    fun onExample(e: ExampleEvent) {
        //e.config
    }

    @EventTarget
    fun onUpdate(event: UpdateEvent) {
        //event.stage
    }

    @EventTarget
    fun onPacket(event: PacketEvent) {
        // event.packet
        // event.cancelEvent()
    }

    @EventTarget
    fun onRender3D(event: Render3DEvent) {
        //event.partialTicks
    }

    @EventTarget
    fun onRender2D(event: Render2DEvent) {
        //event.partialTicks
    }

    @EventTarget
    fun onMotion(event: MotionEvent) {
        //event.x
    }

    @EventTarget
    fun onAttack(event: AttackEvent) {
        //event.targetEntity
    }
}