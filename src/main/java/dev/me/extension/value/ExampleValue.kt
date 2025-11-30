package dev.me.extension.value

import com.google.gson.JsonElement
import com.google.gson.JsonPrimitive
import net.nonemc.leaf.value.Value

/**
 * ExampleValue
 *
 * ===================================
 * ==   DEVELOPER API DOCUMENTATION ==
 * ===================================
 *
 * This class demonstrates how to create a custom Value<T> type
 * that can be used inside the Leaf Value/ClickGUI system.
 *
 * A Value<T> represents a configurable property in a module,
 * and each Value instance is:
 *  - Saved to JSON
 *  - Loaded from JSON
 *  - Displayed through a ValueRenderer
 *  - Editable via the ClickGUI
 *
 *
 * 1. Purpose of ExampleValue
 * ---------------------------
 * ExampleValue is a Double-based Value implementation.
 * It stores:
 *   - name        → display name of this value
 *   - value       → current value (Double)
 *   - minimum     → min clamp
 *   - maximum     → max clamp
 *
 * This class is typically paired with a custom renderer
 * (ExampleValueRenderer) that handles drawing and interaction.
 *
 *
 * 2. Constructor Parameters
 * --------------------------
 * @param name      The display name of this value.
 * @param value     The initial value.
 * @param minimum   Lowest allowed value (default = 0.0).
 * @param maximum   Highest allowed value (default = Double.MAX_VALUE).
 *
 *
 * 3. set(newValue: Number)
 * -------------------------
 * Convenience method allowing developers to set this Value using:
 *   - Int
 *   - Float
 *   - Double
 *   - Any other Number type
 *
 * The Value is then coerced into the valid range.
 *
 *
 * 4. JSON persistence
 * --------------------
 * toJson()    → Converts current value to a JsonPrimitive(Double).
 * fromJson()  → Reads a stored Json value and clamps it properly.
 *
 * These methods allow the Value to be saved inside:
 *   - module configuration files
 *   - presets
 *   - profiles
 *
 *
 * 5. Value<T> behaviors inherited
 * -------------------------------
 * This class inherits from Value<Double>, so it supports:
 *   - get()
 *   - set()
 *   - name
 *   - visibility
 *   - change listeners
 *   - integration with ClickGUI renderer registry
 *
 *
 * 6. Usage Example
 * -----------------
 * Creating a new ExampleValue:
 *
 *     val speed = ExampleValue("Speed", 1.5, 0.1, 5.0)
 *
 * Registering a corresponding renderer:
 *
 *     ValueRenderRegistry.register(ExampleValueRenderer())
 *
 * After this, ClickGUI will automatically display and allow editing.
 *
 */
open class ExampleValue(
    name: String,
    value: Double,
    val minimum: Double = 0.0,
    val maximum: Double = Double.MAX_VALUE
) : Value<Double>(name, value) {

    /**
     * Convenience setter accepting any numeric type.
     * Internally converts to Double and clamps within [minimum, maximum].
     */
    fun set(newValue: Number) {
        set(newValue.toDouble())
    }

    /**
     * Converts this value to a JSON primitive (Double).
     * Used for saving module configuration.
     */
    override fun toJson(): JsonElement = JsonPrimitive(value)

    /**
     * Loads the value from JSON and clamps it within the allowed range.
     * Ignore non-primitive JSON.
     */
    override fun fromJson(element: JsonElement) {
        if (element.isJsonPrimitive) {
            value = element.asDouble.coerceIn(minimum, maximum)
        }
    }
}
