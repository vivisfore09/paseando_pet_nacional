package com.unal.paseando_pet

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario_Entidad {

    @PrimaryKey
    var email:String
    var nombre:String
    var apellidos:String
    var telefono:String
    var direccion:String
    var genero:String
    var password:String

    constructor(
        email: String,
        nombre: String,
        apellidos: String,
        telefono: String,
        direccion: String,
        genero: String,
        password: String
    ) {
        this.email = email
        this.nombre = nombre
        this.apellidos = apellidos
        this.telefono = telefono
        this.direccion = direccion
        this.genero = genero
        this.password = password
    }
}