package dev.me.extension.command

import net.nonemc.leaf.command.ICommand
import net.nonemc.leaf.libs.log.normalLog

/**
 * ExampleCommand
 *
 * ------------------------------------------------------------------------------
 *  OVERVIEW
 * ------------------------------------------------------------------------------
 * This is a basic example of how to create a custom command that integrates
 * with the Leaf client command system. When placed inside an external extension
 * JAR, Leaf will automatically detect and register this command at runtime.
 *
 * ------------------------------------------------------------------------------
 *  HOW COMMANDS ARE LOADED
 * ------------------------------------------------------------------------------
 * External commands are discovered by:
 *  - Scanning the "command" package inside extension modules
 *  - Locating classes that implement ICommand
 *  - Instantiating them dynamically via ExtensionClassLoader
 *  - Registering them into the global command registry
 *
 * Once loaded, the command becomes available in chat using the client prefix:
 *
 *      .example
 *      .exp
 *
 * ------------------------------------------------------------------------------
 *  HOW TO CREATE A CUSTOM COMMAND
 * ------------------------------------------------------------------------------
 * 1. Create a class that implements ICommand
 * 2. Override:
 *      - name()       → the primary command name
 *      - aliases()    → optional command aliases
 *      - complete()   → tab-completion suggestions
 *      - execute()    → command logic
 * 3. Compile the class into an extension JAR
 * 4. Place the extension inside the Leaf extensions directory
 *
 * That's all. Leaf automatically loads and runs your command.
 *
 * ------------------------------------------------------------------------------
 *  TAB COMPLETION
 * ------------------------------------------------------------------------------
 * `complete(args)` receives the arguments AFTER the command name.
 *
 * Example:
 *      User types: ".example extension"
 *      args = ["extension"]
 *
 * Return a list of possible completions based on input.
 *
 * ------------------------------------------------------------------------------
 *  EXECUTION
 * ------------------------------------------------------------------------------
 * `execute(args)` is called when the user runs the command.
 * The arguments passed exclude the command name.
 *
 * ------------------------------------------------------------------------------
 *  EXAMPLE BEHAVIOR
 * ------------------------------------------------------------------------------
 * This command simply prints:
 *
 *      "Example command execution."
 *
 * to the Leaf console/log.
 *
 * ------------------------------------------------------------------------------
 *  TEMPLATE FOR DEVELOPERS
 * ------------------------------------------------------------------------------
 * You may copy this file as a template:
 *
 *      class MyCommand : ICommand {
 *          override fun name() = "mycmd"
 *          override fun aliases() = listOf("mc")
 *
 *          override fun complete(args: List<String>): List<String> {
 *              // return completion list
 *              return emptyList()
 *          }
 *
 *          override fun execute(args: List<String>) {
 *              normalLog("MyCommand executed with args: $args")
 *          }
 *      }
 *
 * ------------------------------------------------------------------------------
 */

class ExampleCommand : ICommand {

    /** Primary command name. Used like: .example */
    override fun name() = "example"

    /** Optional aliases. Used like: .exp */
    override fun aliases() = listOf("exp")

    /**
     * Provides tab-completion suggestions.
     *
     * @param args arguments typed by user (excluding command name)
     * @return list of suggestions
     */
    override fun complete(args: List<String>): List<String> {
        return when (args.size) {
            else -> emptyList() // No suggestions in this simple example
        }
    }

    /**
     * Command execution logic.
     *
     * @param args arguments typed by user (excluding command name)
     */
    override fun execute(args: List<String>) {
        normalLog("Example command execution.")
    }
}
