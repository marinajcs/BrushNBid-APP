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
    @SerializedName("precio_inicial") val precioInicial: Double?,
    val incremento: Double?,
    @SerializedName("precio_reserva") val precioReserva: Double?,
    val duracion: Int?,
    val adjudicado: Boolean
)

data class SubastaInfo(
    @SerializedName("subasta_id") val subastaId: Int,
    @SerializedName("obra_id") val obraId: Int,
    @SerializedName("obra_nombre") val obraNombre: String,
    @SerializedName("vendedor_fullname") val vendedor: String,
    @SerializedName("puja_actual") val pujaActual: String,
    @SerializedName("fecha_fin") val fechaFin: String?,
    @SerializedName("obra_imagen") val image: String,
    val adjudicado: Boolean,
    @SerializedName("mejor_postor") val mejorPostor: String
)

data class SubastaInput(
    @SerializedName("obra_id") val obraId: Int,
    @SerializedName("vendedor_id") val vendedorId: Int,
    @SerializedName("precio_inicial") val precioInicial: Double?,
    val incremento: Double?,
    @SerializedName("precio_reserva") val precioReserva: Double?,
    @SerializedName("compra_inmediata") val compraInmediata: Double?,
    val duracion: Int?
)