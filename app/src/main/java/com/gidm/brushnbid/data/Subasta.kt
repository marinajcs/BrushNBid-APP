package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName
/*
data class Subasta(
    val obra: String,
    val vendedor: String,
    val image: String,
    val precioInicial: Double = 0.0,
    val fechaInicio: String = "",
    val duracion: Double = 7.0,
    val fechaFinal: String? = "",
    val incremento: Double? = null, // Incremento mínimo de puja (opcional)
    val precioReserva: Double? = null, // Opcional y oculto
    //val compraInmediata: Double? = null // Compra inmediata (opcional)
)
*/

data class SubastaSummary(
    val obra: String,
    val vendedor: String,
    val image: String,
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
