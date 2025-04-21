package com.gidm.brushnbid.data

data class User(
    var nombreCompleto: String,
    var username: String,
    var email: String,
    var password: String,
    var pais: String,
    var direccion: String,
    var rol: Rol,
    var subastasSeguidas: List<Subasta> = listOf()
)