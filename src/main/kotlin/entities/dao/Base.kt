package mx.com.blackengine.entities.dao

import mx.com.blackengine.entities.tables.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class EnumClass(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, EnumClass>(Enums)
}

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

    var name by Lots.name
}