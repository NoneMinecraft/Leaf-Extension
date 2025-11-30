package dev.me.extension.value.handler

import dev.me.extension.value.ExampleValue
import net.nonemc.leaf.value.Value
import net.nonemc.leaf.value.handler.IValueHandler

/**
 * ExampleValueHandler
 *
 * ===================================
 * ==   DEVELOPER API DOCUMENTATION ==
 * ===================================
 *
 * This class provides command-side support for ExampleValue.
 *
 * Leaf's command system allows modules to modify Value<T> fields
 * through text commands such as:
 *
 *     .module speed 2.5
 *
 * To make a Value<T> compatible with command input, it must have
 * a corresponding IValueHandler implementation.
 *
 * This handler enables:
 *   - Parsing user input (String → Double)
 *   - Setting the converted value into ExampleValue
 *   - Providing completion hints for TAB-complete
 *
 *
 * 1. Purpose of ExampleValueHandler
 * --------------------------------
 * This handler allows ExampleValue (Double-based value) to be edited
 * using Leaf's built-in command interface.
 *
 * Without a handler, ExampleValue would be considered "unsupported"
 * when editing via commands.
 *
 *
 * 2. canHandle(value)
 * -------------------
 * @return true if the provided Value<T> is an ExampleValue.
 *
 * This tells the command system:
 *   "When a module's value is ExampleValue, use this handler."
 *
 *
 * 3. setValue(value, input)
 * --------------------------
 * Converts input string into a Double and sets it into the ExampleValue.
 *
 * @throws IllegalArgumentException
 *         If input is not a valid number.
 *
 * Value itself will clamp based on minimum/maximum restrictions.
 *
 *
 * 4. complete(value, input)
 * --------------------------
 * Provides TAB-completion for command suggestions.
 *
 * Returning "<number>" indicates that this field expects numeric input.
 *
 *
 * 5. Usage Example
 * ----------------
 * Registration:
 *
 *     ValueHandlerRegistry.register(ExampleValueHandler())
 *
 * Command usage after registration:
 *
 *     .module speed 4.2
 *
 * Completion hint:
 *
 *     <number>
 */
class ExampleValueHandler : IValueHandler<ExampleValue> {

    /** Determines if this handler supports the specified value type. */
    override fun canHandle(value: Value<*>) = value is ExampleValue

    /**
     * Attempts to parse the input String as a Double.
     * Throws IllegalArgumentException if parsing fails.
     */
    override fun setValue(value: ExampleValue, input: String) {
        val number = input.toDoubleOrNull() ?: throw IllegalArgumentException("Not a valid number")
        value.set(number)
    }

    /**
     * Returns a simple placeholder to guide command tab completion.
     */
    override fun complete(value: ExampleValue, input: String) = listOf("<number>")
}
