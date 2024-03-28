package mx.com.blackengine.entities.dao

import com.github.shamil.Xid
import mx.com.blackengine.entities.tables.Configs
import mx.com.blackengine.entities.tables.Enums
import mx.com.blackengine.entities.tables.Products
import mx.com.blackengine.entities.tables.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EnumClass(id: EntityID<Xid>): Entity<Xid>(id) {
    companion object: EntityClass<Xid,EnumClass>(Enums)
}

class Config(id: EntityID<Xid>): Entity<Xid>(id){
    companion object: EntityClass<Xid,Config>(Configs)
}

class Product(id: EntityID<Xid>): Entity<Xid>(id){
    companion object: EntityClass<Xid,Product>(Products)
}

class User(id: EntityID<Xid>): Entity<Xid>(id){
    companion object: EntityClass<Xid,User>(Users)
}