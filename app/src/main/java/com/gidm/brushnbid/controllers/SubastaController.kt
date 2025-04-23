package com.gidm.brushnbid.controllers

import com.gidm.brushnbid.api.ApiClient
import com.gidm.brushnbid.api.ApiService
import com.gidm.brushnbid.data.Puja
import com.gidm.brushnbid.data.Subasta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SubastaController {
    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    // Obtener todas las subastas
    fun getAllSubastas(onSuccess: (List<Subasta>) -> Unit, onError: (String) -> Unit) {
        apiService.getAllSubastas().enqueue(object : Callback<List<Subasta>> {
            override fun onResponse(call: Call<List<Subasta>>, response: Response<List<Subasta>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Subasta>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener subastas activas
    fun getActiveSubastas(onSuccess: (List<Subasta>) -> Unit, onError: (String) -> Unit) {
        apiService.getActiveSubastas().enqueue(object : Callback<List<Subasta>> {
            override fun onResponse(call: Call<List<Subasta>>, response: Response<List<Subasta>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Subasta>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener una subasta por ID
    fun getSubastaById(id: Int, onSuccess: (Subasta) -> Unit, onError: (String) -> Unit) {
        apiService.getSubastaById(id).enqueue(object : Callback<Subasta> {
            override fun onResponse(call: Call<Subasta>, response: Response<Subasta>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Subasta no encontrada")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Subasta>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Crear una nueva subasta
    fun createSubasta(
        subasta: Subasta,
        onSuccess: (Subasta) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.createSubasta(subasta).enqueue(object : Callback<Subasta> {
            override fun onResponse(call: Call<Subasta>, response: Response<Subasta>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al crear subasta")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Subasta>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Actualizar una subasta existente
    fun updateSubasta(
        id: Int,
        subasta: Subasta,
        onSuccess: (Subasta) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.updateSubasta(id, subasta).enqueue(object : Callback<Subasta> {
            override fun onResponse(call: Call<Subasta>, response: Response<Subasta>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al actualizar subasta")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Subasta>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Eliminar una subasta
    fun deleteSubasta(
        id: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.deleteSubasta(id).enqueue(object : Callback<Void> {
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

    // Obtener las pujas de una subasta
    fun getPujasBySubastaId(
        id: Int,
        onSuccess: (List<Puja>) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.getPujasBySubastaId(id).enqueue(object : Callback<List<Puja>> {
            override fun onResponse(call: Call<List<Puja>>, response: Response<List<Puja>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("No hay pujas")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Puja>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Realizar una puja en una subasta
    fun addPuja(
        subastaId: Int,
        puja: Puja,
        onSuccess: (Puja) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.addPuja(subastaId, puja).enqueue(object : Callback<Puja> {
            override fun onResponse(call: Call<Puja>, response: Response<Puja>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al hacer la puja")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Puja>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }
}
