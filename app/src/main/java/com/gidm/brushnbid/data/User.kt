package com.gidm.brushnbid.data

data class User(
    val nombreCompleto: String,
    val username: String,
    val email: String,
    val password: String,
    val pais: String,
    val direccion: String,
    val rol: Rol,
    val subastasSeguidas: List<Subasta> = listOf()
)