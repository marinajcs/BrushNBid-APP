package com.gidm.brushnbid.data

data class Subasta(
    var obra: String,
    var vendedor: String,
    var image: String,
    var precioInicial: Double = 0.0,
    var fechaInicio: String = "",
    var duracion: Double = 7.0,
    var fechaFinal: String? = "",
    var incremento: Double? = null, // Incremento mínimo de puja (opcional)
    var precioReserva: Double? = null, // Opcional y oculto
    var compraInmediata: Double? = null // Compra inmediata (opcional)

)
