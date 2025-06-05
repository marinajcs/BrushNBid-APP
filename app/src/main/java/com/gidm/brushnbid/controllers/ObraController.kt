package com.gidm.brushnbid.controllers

import android.content.Context
import com.gidm.brushnbid.api.ApiClient
import com.gidm.brushnbid.api.ApiService
import com.gidm.brushnbid.data.Obra
import com.gidm.brushnbid.data.ObraInfo
import com.gidm.brushnbid.data.ObraInput
import com.gidm.brushnbid.data.ObraSummary
import com.gidm.brushnbid.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class ObraController {
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    fun getAllObras(onSuccess: (List<Obra>) -> Unit, onError: (String) -> Unit) {
        apiService.getAllObras().enqueue(object : Callback<List<Obra>> {
            override fun onResponse(call: Call<List<Obra>>, response: Response<List<Obra>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Obra>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener una obra por su ID
    fun getObraById(id: Int, onSuccess: (Obra) -> Unit, onError: (String) -> Unit) {
        apiService.getObraById(id).enqueue(object : Callback<Obra> {
            override fun onResponse(call: Call<Obra>, response: Response<Obra>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Obra no encontrada")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Obra>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    fun getObraInfoById(id: Int, onSuccess: (ObraInfo) -> Unit, onError: (String) -> Unit) {
        apiService.getObraInfoById(id).enqueue(object : Callback<ObraInfo> {
            override fun onResponse(call: Call<ObraInfo>, response: Response<ObraInfo>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Obra no encontrada")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ObraInfo>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }


    fun getObrasByUser(userId: Int, onSuccess: (List<Obra>) -> Unit, onError: (String) -> Unit) {
        apiService.getObrasByUser(userId).enqueue(object : Callback<List<Obra>> {
            override fun onResponse(call: Call<List<Obra>>, response: Response<List<Obra>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Obra>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    fun getObrasDisponiblesByUser(userId: Int, onSuccess: (List<Obra>) -> Unit, onError: (String) -> Unit) {
        apiService.getObrasDisponiblesByUser(userId).enqueue(object : Callback<List<Obra>> {
            override fun onResponse(call: Call<List<Obra>>, response: Response<List<Obra>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Obra>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Crear una nueva obra
    fun createObra(
        obra: ObraInput,
        onSuccess: (ObraInput) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.createObra(obra).enqueue(object : Callback<ObraInput> {
            override fun onResponse(call: Call<ObraInput>, response: Response<ObraInput>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al crear obra")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<ObraInput>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }

        })
    }

    // Actualizar una obra existente
    fun updateObra(
        id: Int,
        obra: Obra,
        onSuccess: (Obra) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.updateObra(id, obra).enqueue(object : Callback<Obra> {
            override fun onResponse(call: Call<Obra>, response: Response<Obra>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al actualizar obra")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Obra>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Eliminar una obra
    fun deleteObra(
        id: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.deleteObra(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    fun getObraImageDir(context: Context): File {
        val dir = File(context.filesDir, "obras")
        if (!dir.exists()) dir.mkdirs()
        return dir
    }

}



