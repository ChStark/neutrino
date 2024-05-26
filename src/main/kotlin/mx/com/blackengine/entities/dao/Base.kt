package mx.com.blackengine.entities.dao

import mx.com.blackengine.entities.tables.neutrino.Configs
import mx.com.blackengine.entities.tables.neutrino.Lots
import mx.com.blackengine.entities.tables.neutrino.Products
import mx.com.blackengine.entities.tables.neutrino.Users
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