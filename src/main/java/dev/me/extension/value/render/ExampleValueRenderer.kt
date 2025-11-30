package dev.me.extension.value.render

import dev.me.extension.value.ExampleValue
import net.nonemc.leaf.value.*
import net.nonemc.leaf.font.Fonts
import net.nonemc.leaf.libs.render.RenderUtils
import net.nonemc.leaf.value.render.IValueRenderer

/**
 * ExampleValueRenderer
 *
 * This class demonstrates how developers can create custom renderers
 * for their own Value types and plug them into the Leaf ClickGUI system.
 *
 * =============================
 * ==  DEVELOPER DOCUMENTATION ==
 * =============================
 *
 * 1. What is a ValueRenderer?
 * ---------------------------
 * A ValueRenderer controls how a Value<T> is drawn and interacted with
 * in the ClickGUI. Each renderer handles:
 *   - Rendering
 *   - Mouse clicks
 *   - Mouse dragging
 *   - Typing input (optional)
 *   - Height calculation
 *
 *
 * 2. How to register your renderer?
 * ---------------------------------
 * In your mod initialization:
 *
 *     ValueRenderRegistry.register(ExampleValueRenderer())
 *
 * After registration, ClickGUI can automatically discover your renderer.
 *
 *
 * 3. How does ClickGUI use this renderer?
 * ---------------------------------------
 * In the ClickGUI system:
 *   - When a Value needs to be drawn,
 *     ClickGUI asks the registry for a renderer that `canHandle(value)`.
 *
 *   - If this renderer returns true, ClickGUI delegates rendering
 *     and input handling to your renderer.
 *
 *
 * 4. Important implementation rules
 * ---------------------------------
 *  - Your renderer must NOT modify default behavior of base Value types.
 *  - You MAY render only the custom Value types you create.
 *  - Height must be a fixed integer.
 *  - All interactions (dragging, click detection) must be handled internally.
 *
 *
 * 5. Threading / safety notes
 * ---------------------------
 *  - Render code runs every frame → must be lightweight.
 *  - Do not store large objects inside renderers.
 *  - Use identityHashCode(value) if you must track per-Value interaction state.
 *
 *
 * 6. Coordinate system
 * ---------------------
 *  x, y     = top-left of this Value area in ClickGUI
 *  width    = width of Value panel
 *  mouseX   = GUI-scaled mouse X
 *  mouseY   = GUI-scaled mouse Y
 *
 */
class ExampleValueRenderer : IValueRenderer {

    /**
     * Tracks which ExampleValue instances are currently being dragged.
     * Using identityHashCode ensures different Value instances do not collide,
     * even if they contain the same number.
     */
    private val dragging = mutableSetOf<Int>()

    /**
     * Determines whether this renderer can handle the given Value.
     * Return true only for ExampleValue (your custom Value).
     */
    override fun canHandle(value: Value<*>) = value is ExampleValue

    /**
     * Returns the height required to display this Value inside ClickGUI.
     * Must be a constant integer.
     */
    override fun getHeight(value: Value<*>, width: Int) = 26

    /**
     * Renders your custom Value.
     *
     * @param value     The Value being rendered
     * @param x, y      Top-left coordinate of the Value panel
     * @param width     Width of the panel
     * @param mouseX    Mouse X position (ClickGUI scaled)
     * @param mouseY    Mouse Y (ClickGUI scaled)
     * @param guiColor  Theme color used by ClickGUI
     */
    override fun render(
        value: Value<*>,
        x: Int,
        y: Int,
        width: Int,
        mouseX: Int,
        mouseY: Int,
        guiColor: Int
    ) {
        val dv = value as ExampleValue
        val name = dv.name + ": " + String.format("%.2f", dv.get())

        // Draw value label
        Fonts.font35.drawString(name, x + 2, y + 4, -0x1)

        // Slider bars
        val sliderTop = y + 19f
        val sliderLeft = x + 1f
        val sliderRight = x + width - 1f
        val knobW = 2f

        // Background slider line
        RenderUtils.drawRect(
            sliderLeft, sliderTop + 0.5f,
            sliderRight, sliderTop + 1.5f,
            -0xcccccd
        )

        // Calculate knob position
        var fraction = ((dv.get() - dv.minimum) / (dv.maximum - dv.minimum)).toDouble()
        if (fraction.isNaN() || fraction.isInfinite()) fraction = 0.0
        fraction = fraction.coerceIn(0.0, 1.0)
        val usable = (sliderRight - sliderLeft) - knobW
        val knobX = sliderLeft + (fraction.toFloat() * usable)

        // Draw slider knob
        RenderUtils.drawRect(
            knobX,
            sliderTop - 1.5f,
            knobX + knobW,
            sliderTop + 2.5f,
            guiColor
        )
    }

    /**
     * Called when user clicks inside the Value panel.
     * Return true if the renderer consumes the event.
     */
    override fun mouseClicked(
        value: Value<*>,
        x: Int,
        y: Int,
        width: Int,
        mouseX: Int,
        mouseY: Int,
        mouseButton: Int
    ): Boolean {
        val dv = value as ExampleValue
        val sliderTop = y + 19
        val sliderBottom = y + 26

        // Begin drag if clicking on slider
        if (mouseButton == 0 && mouseY in sliderTop..sliderBottom) {
            dragging.add(System.identityHashCode(dv))
            return true
        }
        return false
    }

    /**
     * Called when mouse is released.
     */
    override fun mouseReleased(
        value: Value<*>,
        x: Int,
        y: Int,
        width: Int,
        mouseX: Int,
        mouseY: Int,
        state: Int
    ): Boolean {
        val dv = value as ExampleValue
        val id = System.identityHashCode(dv)
        if (dragging.remove(id)) return true
        return false
    }

    /**
     * Called during mouse dragging or scroll wheel input.
     * Used here to drag the slider knob.
     */
    override fun handleMouseInput(
        value: Value<*>,
        x: Int,
        y: Int,
        width: Int,
        guiMouseX: Int,
        guiMouseY: Int,
        wheel: Int
    ): Boolean {
        val dv = value as ExampleValue
        val id = System.identityHashCode(dv)

        // If not dragging, ignore input
        if (!dragging.contains(id)) return false

        // Compute slider fraction from mouse X
        val sliderLeft = x + 1f
        val sliderRight = x + width - 1f
        val knobW = 2f

        var i = (guiMouseX - sliderLeft - knobW / 2.0) /
                (sliderRight - sliderLeft - knobW).toDouble()

        i = i.coerceIn(0.0, 1.0)
        dv.set(dv.minimum + (dv.maximum - dv.minimum) * i)

        return true
    }

    /**
     * Handles keyboard typing when this Value is focused.
     * ExampleValue has no text input, so return false.
     */
    override fun keyTyped(value: Value<*>, typedChar: Char, keyCode: Int): Boolean {
        return false
    }
}
