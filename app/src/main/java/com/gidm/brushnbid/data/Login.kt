package com.gidm.brushnbid.data

data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val token: String,
    val message: String,
    val userId: Int,
    val username: String,
    val email: String,
    val fullname: String
)
