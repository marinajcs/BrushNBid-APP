package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName


data class Obra(
    val id: Int,
    val titulo: String,
    @SerializedName("autoria_id") val autoriaId: Int,
    @SerializedName("propiedad_id") val propiedadId: Int,
    val tipo: String,
    val descripcion: String,
    val imagen: String
)


data class ObraSummary(
    val id: Int,
    val titulo: String,
    val estado: Estado,
    val imagen: String
)

enum class Estado{
    ACTIVA, VENDIDA
}

data class ObraInfo(
    val titulo: String,
    @SerializedName("autoria_nombre") val autoria: String,
    @SerializedName("propiedad_nombre") val propiedad: String,
    val tipo: String,
    val descripcion: String,
    val imagen: String
)

data class ObraInput(
    val titulo: String,
    @SerializedName("autoria_id") val autoriaId: Int,
    @SerializedName("propiedad_id") val propiedadId: Int,
    val tipo: String,
    val descripcion: String,
    val imagen: String
)