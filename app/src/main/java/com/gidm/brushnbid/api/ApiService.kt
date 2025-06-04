package com.gidm.brushnbid.api

import com.gidm.brushnbid.data.Adjudicacion
import com.gidm.brushnbid.data.LoginRequest
import com.gidm.brushnbid.data.LoginResponse
import com.gidm.brushnbid.data.Obra
import com.gidm.brushnbid.data.ObraInfo
import com.gidm.brushnbid.data.ObraInput
import com.gidm.brushnbid.data.ObraSummary
import com.gidm.brushnbid.data.Puja
import com.gidm.brushnbid.data.PujaInput
import com.gidm.brushnbid.data.Subasta
import com.gidm.brushnbid.data.SubastaInput
import com.gidm.brushnbid.data.SubastaSummary
import com.gidm.brushnbid.data.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    // Usuarios
    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>

    @POST("login")
    fun login(@Body loginData: Map<String, String>): Call<Map<String, String>>

    @POST("logout")
    fun logout(): Call<Void>

    //---------------------------------------------------------

    // Obtener todas las obras
    @GET("/obras")
    fun getAllObras(): Call<List<Obra>>

    // Obtener una obra por ID
    @GET("/obras/{id}")
    fun getObraById(@Path("id") id: Int): Call<Obra>

    @GET("/obras/{id}/info")
    fun getObraInfoById(@Path("id") id: Int): Call<ObraInfo>

    // Obtener las obras de un usuario específico
    @GET("/obras/user/{id}")
    fun getObrasByUser(@Path("id") userId: Int): Call<List<Obra>>

    // Crear una nueva obra
    @POST("/obras")
    fun createObra(@Body obra: ObraInput): Call<ObraInput>

    // Actualizar una obra
    @PUT("/obras/{id}")
    fun updateObra(@Path("id") id: Int, @Body obra: Obra): Call<Obra>

    // Eliminar una obra por ID
    @DELETE("/obras/{id}")
    fun deleteObra(@Path("id") id: Int): Call<Void>

    //---------------------------------------------------------

    // Obtener todas las subastas
    @GET("/subastas")
    fun getAllSubastas(): Call<List<Subasta>>

    // Obtener subastas activas
    @GET("/subastas/activas")
    fun getActiveSubastas(): Call<List<SubastaSummary>>

    // Obtener una subasta por ID
    @GET("/subastas/{id}")
    fun getSubastaById(@Path("id") id: Int): Call<Subasta>

    // Crear una nueva subasta
    @POST("/subastas")
    fun createSubasta(@Body subasta: Subasta): Call<Subasta>

    // Actualizar una subasta
    @PUT("/subastas/{id}")
    fun updateSubasta(@Path("id") id: Int, @Body subasta: Subasta): Call<Subasta>

    // Eliminar una subasta
    @DELETE("/subastas/{id}")
    fun deleteSubasta(@Path("id") id: Int): Call<Void>

    // Obtener las pujas de una subasta
    @GET("/subastas/{id}/pujas")
    fun getPujasBySubastaId(@Path("id") id: Int): Call<List<Puja>>

    // Realizar una puja
    @POST("/subastas/{id}/pujas")
    fun addPuja(@Path("id") subastaId: Int, @Body puja: Puja): Call<Puja>

    // Adjudicar la subasta al mejor postor
    @POST("/subastas/{id}/adjudicar")
    fun adjudicarSubasta(@Path("id") id: Int): Call<Void>
}
