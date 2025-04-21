package com.gidm.brushnbid.data

data class Obra(
    var titulo: String,
    var autoria: User,          // Relación con Usuario en lugar de String
    var propiedad: User,         // Quien la tiene actualmente
    var tipo: TipoObra,
    var imagen: String,            // URL o ruta de la imagen
    var descripcion: String
)