package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName

data class Puja(
    val id: Int,
    @SerializedName("subasta_id") val subastaId: Int,
    @SerializedName("user_id") val userId: Int,
    val cantidad: Double
)

data class PujaInput(
    @SerializedName("user_id") val userId: Int,
    val cantidad: Double
)

data class PujaInfo(
    val username: String,
    val cantidad: Double,
    val fecha: String
)