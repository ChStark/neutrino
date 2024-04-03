package mx.com.blackengine.entities.tables

import mx.com.blackengine.entities.columns.InsertedUpdatedDeletedTable
import mx.com.blackengine.entities.columns.InsertedUpdatedTable
import mx.com.blackengine.entities.columns.NameableInsertedUpdatedDeletedTable

object Suppliers : NameableInsertedUpdatedDeletedTable("suppliers")

object PurchaseOrderCycles : InsertedUpdatedTable("purchase_order_cycles")

object PurchaseOrders : InsertedUpdatedDeletedTable("purchase_orders")

object PurchaseOrderLines : InsertedUpdatedDeletedTable("purchase_order_lines")