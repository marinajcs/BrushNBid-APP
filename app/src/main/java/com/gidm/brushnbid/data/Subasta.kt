package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName

data class SubastaSummary(
    @SerializedName("subasta_id") val subastaId: Int,
    @SerializedName("obra_titulo") val obra: String,
    @SerializedName("vendedor_fullname") val vendedor: String,
    @SerializedName("obra_imagen") val image: String,
)

data class Subasta(
    val id: Int,
    @SerializedName("obra_id") val obraId: Int,
    @SerializedName("vendedor_id") val vendedorId: Int,
    @SerializedName("precio_inicial") val precioInicial: Double,
    val incremento: Double,
    @SerializedName("precio_reserva") val precioReserva: Double?,
    @SerializedName("fecha_inicio") val fechaInicio: String,
    val duracion: Int?,
    val adjudicado: Boolean
)
