package mx.com.blackengine.entities.dao

import com.github.shamil.Xid
import mx.com.blackengine.entities.tables.*
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

class EnumClass(id: EntityID<Xid>) : Entity<Xid>(id) {
    companion object : EntityClass<Xid, EnumClass>(Enums)
}

class Config(id: EntityID<Xid>) : Entity<Xid>(id) {
    companion object : EntityClass<Xid, Config>(Configs)
}

class Product(id: EntityID<Xid>) : Entity<Xid>(id) {
    companion object : EntityClass<Xid, Product>(Products)
}

class User(id: EntityID<Xid>) : Entity<Xid>(id) {
    companion object : EntityClass<Xid, User>(Users)
}

class Lot(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, Lot>(Lots)

    var name by Lots.name
}