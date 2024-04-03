package mx.com.blackengine.mx.com.blackengine

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import mx.com.blackengine.commands.*

class Neutrino : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = mx.com.blackengine.mx.com.blackengine.Neutrino()
    .subcommands(
        Automigrate(),
        DbLocalTest(),
        Backoffice(),
        Marketplace(),
        Test(),
    )
    .main(args)