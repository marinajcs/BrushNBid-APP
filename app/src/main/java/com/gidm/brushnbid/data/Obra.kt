package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName

data class Obra(
    val titulo: String,
    @SerializedName("autoria_id") val autoriaId: Int,          // Relación con Usuario en lugar de String
    @SerializedName("propiedad_id") val propiedadId: Int,         // Quien la tiene actualmente
    val tipo: String,
    val descripcion: String,
    val imagen: String            // URL o ruta de la imagen
)

data class ObraSummary(
    val titulo: String,
    val estado: Estado,
    val imagen: Int
)

enum class Estado{
    ACTIVA, VENDIDA
}