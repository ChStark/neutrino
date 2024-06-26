package mx.com.blackengine

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands
import mx.com.blackengine.commands.AutomigrateNeutrino
import mx.com.blackengine.commands.Backoffice
import mx.com.blackengine.commands.DbLocalTest
import mx.com.blackengine.commands.Test

class Neutrino : CliktCommand() {
    override fun run() = Unit
}

fun main(args: Array<String>) = Neutrino()
    .subcommands(
        AutomigrateNeutrino(),
        DbLocalTest(),
        Backoffice(),
        Test(),
    )
    .main(args)