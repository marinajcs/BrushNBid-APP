package com.gidm.brushnbid.data

data class SubastaInput(
    val obra_id: Int,
    val vendedor_id: Int,
    val precio_inicial: Double,
    val incremento: Double,
    val precio_reserva: Double?,
    val fecha_inicio: String,
    val duracion: Int
)