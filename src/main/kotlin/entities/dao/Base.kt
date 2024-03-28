package mx.com.blackengine.entities.dao

import mx.com.blackengine.entities.tables.Configs
import mx.com.blackengine.entities.tables.Enums
import mx.com.blackengine.entities.tables.Products
import mx.com.blackengine.entities.tables.Users
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EnumClass(id: EntityID<String>): Entity<String>(id) {
    companion object: EntityClass<String,EnumClass>(Enums)
}

class Config(id: EntityID<String>): Entity<String>(id){
    companion object: EntityClass<String,Config>(Configs)
}

class Product(id: EntityID<String>): Entity<String>(id){
    companion object: EntityClass<String,Product>(Products)
}

class User(id: EntityID<String>): Entity<String>(id){
    companion object: EntityClass<String,User>(Users)
}