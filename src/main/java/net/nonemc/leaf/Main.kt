package net.nonemc.leaf

/**
 * Main
 *
 * ------------------------------------------------------------------------------
 *  OVERVIEW
 * ------------------------------------------------------------------------------
 * This object serves as the main configuration entry point for the Leaf extension
 * framework. It defines which packages and classes the framework will scan to
 * automatically discover and register various components such as modules,
 * commands, events, and UI elements.
 *
 * All fields in this object are used for **automatic registration**. Items not
 * covered here usually require manual registration.
 *
 * ------------------------------------------------------------------------------
 *  COMPONENTS
 * ------------------------------------------------------------------------------
 *
 * 1. Module
 *      - Functional units of the client.
 *      - Must extend the Module base class.
 *      - Automatic registration if placed in the package specified in `module`.
 *
 * 2. Command
 *      - Console/chat commands to control modules or client features.
 *      - Must implement the ICommand interface.
 *      - Automatic registration if placed in the package specified in `command`.
 *
 * 3. Event
 *      - Automatic registration for events.
 *      - The target class must inherit Event() or CancelableEvent().
 *      - Triggered using `callEvent(event)` function.
 *      - No manual registration needed.
 *
 * 4. Sound
 *      - Custom sound playback element.
 *      - Requires manual implementation and registration.
 *      - Must call `initSounds()` to initialize and register sounds.
 *
 * 5. Value
 *      - Represents configurable values for modules (BoolValue, FloatValue, ListValue, TextValue, etc.)
 *      - Must be manually registered.
 *      - Can be dynamically adjusted using a ValueHandler or console commands.
 *
 * 6. MainMenu / UI
 *      - Represents the main interface of the client.
 *      - Automatic registration if fully qualified class name matches `mainMenu`.
 *
 * ------------------------------------------------------------------------------
 *  FIELD DEFINITIONS
 * ------------------------------------------------------------------------------
 *
 * - mainPackage: String
 *      - Base package pattern for Leaf to scan for all supported extensions.
 *      - Example: "dev.*.leaf."
 *
 * - module: String
 *      - Package pattern specifically for Module classes.
 *      - Example: "module.*"
 *
 * - command: String
 *      - Package pattern specifically for ICommand implementations.
 *      - Example: "command.*"
 *
 * - mainMenu: String
 *      - Fully qualified class name of the MainMenu class.
 *      - Example: "ui.mainmenu.MainMenu"
 *
 * ------------------------------------------------------------------------------
 *  SUMMARY FOR DEVELOPERS
 * ------------------------------------------------------------------------------
 *
 *  - Place modules, commands, events, and UI classes under the appropriate packages.
 *  - Leaf automatically detects and registers components at runtime.
 *  - Manual registration is required for Value and Sound elements.
 *  - Interfaces / Requirements:
 *      - Module → extend Module class
 *      - Command → implement ICommand
 *      - Event → extend Event() or CancelableEvent()
 *      - Sound → implement custom sound and call initSounds()
 *      - UI → extend/implement custom main menu class
 */

@Suppress("UNUSED")
object Main {
    val mainPackage = "dev.*.extension."

    val module = "module.*"

    val command = "command.*"

    val mainMenu = "ui.mainmenu.MainMenu"
}