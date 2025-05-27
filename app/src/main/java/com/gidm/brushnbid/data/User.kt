package com.gidm.brushnbid.data

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("full_name") val fullname: String,
    val username: String,
    val email: String,
    val password: String,
    val direccion: String
)