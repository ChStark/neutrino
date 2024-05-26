package mx.com.blackengine.entities.tables.neutrino

import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.TimestampTable
import mx.com.blackengine.entities.columns.citext

object LedgerAccounts : InsertedUpdatedDeletedTable("ledger_accounts") {
    val type = reference("type", EnumLedgerAccountTypes) //get the credit/debit nature from the type
    val name = citext("name")
}

object LedgerJournalEntries : TimestampTable("ledger_journal_entries") {
    val account = reference("account", LedgerAccounts)
    val debit = integer("debit").default(0).check { it greaterEq 0 }
    val credit = integer("credit").default(0).check { it greaterEq 0 }
}