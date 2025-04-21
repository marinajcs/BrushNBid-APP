package com.gidm.brushnbid.data

data class Subasta(
    var obra: Obra,
    var vendedor: User,
    var precioInicial: Double = 0.0,
    var incremento: Double? = null, // Incremento mínimo de puja (opcional)
    var precioReserva: Double? = null, // Opcional y oculto
    var compraInmediata: Double? = null, // Compra inmediata (opcional)
    var fechaInicio: String,
    var duracion: Double,
    var fechaFinal: String
)
