package mx.com.blackengine

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import mx.com.blackengine.commands.Automigrate
import mx.com.blackengine.commands.Backoffice
import mx.com.blackengine.commands.Marketplace

class Neutrino : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = Neutrino()
    .subcommands(
        Automigrate(),
        Backoffice(),
        Marketplace(),
    )
    .main(args)