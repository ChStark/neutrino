package mx.com.blackengine

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import mx.com.blackengine.commands.*

class Neutrino : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = Neutrino()
    .subcommands(
        Automigrate(),
        DbLocalTest(),
        Backoffice(),
        Marketplace(),
        Test(),
    )
    .main(args)