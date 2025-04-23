package com.gidm.brushnbid.data

data class Adjudicacion(
    val message: String,
    val mejorPuja: Puja,
    val devoluciones: List<Puja>
)
