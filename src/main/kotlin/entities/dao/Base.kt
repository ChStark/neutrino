package mx.com.blackengine.entities.dao

import mx.com.blackengine.entities.tables.Configs
import mx.com.blackengine.entities.tables.Lots
import mx.com.blackengine.entities.tables.Products
import mx.com.blackengine.entities.tables.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class Config(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Config>(Configs)
}

class Product(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Product>(Products)
}

class User(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, User>(Users)
}

class Lot(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Lot>(Lots)
}