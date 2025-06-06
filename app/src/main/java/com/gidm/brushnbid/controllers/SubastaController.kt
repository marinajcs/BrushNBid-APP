package com.gidm.brushnbid.controllers

import com.gidm.brushnbid.api.ApiClient
import com.gidm.brushnbid.api.ApiService
import com.gidm.brushnbid.data.Puja
import com.gidm.brushnbid.data.PujaInfo
import com.gidm.brushnbid.data.PujaInput
import com.gidm.brushnbid.data.Subasta
import com.gidm.brushnbid.data.SubastaInfo
import com.gidm.brushnbid.data.SubastaInput
import com.gidm.brushnbid.data.SubastaSummary
import org.json.JSONObject
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
    fun getActiveSubastas(onSuccess: (List<SubastaSummary>) -> Unit, onError: (String) -> Unit) {
        apiService.getActiveSubastas().enqueue(object : Callback<List<SubastaSummary>> {
            override fun onResponse(call: Call<List<SubastaSummary>>, response: Response<List<SubastaSummary>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SubastaSummary>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener subastas activas de un user
    fun getActiveSubastasByUser(id: Int, onSuccess: (List<SubastaSummary>) -> Unit, onError: (String) -> Unit) {
        apiService.getActiveSubastasByUser(id).enqueue(object : Callback<List<SubastaSummary>> {
            override fun onResponse(call: Call<List<SubastaSummary>>, response: Response<List<SubastaSummary>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SubastaSummary>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener subastas finalizadas de un user
    fun getFinishedSubastasByUser(id: Int, onSuccess: (List<SubastaSummary>) -> Unit, onError: (String) -> Unit) {
        apiService.getFinishedSubastasByUser(id).enqueue(object : Callback<List<SubastaSummary>> {
            override fun onResponse(call: Call<List<SubastaSummary>>, response: Response<List<SubastaSummary>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SubastaSummary>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener subastas seguidas por un user
    fun getFollowedSubastasByUser(id: Int, onSuccess: (List<SubastaSummary>) -> Unit, onError: (String) -> Unit) {
        apiService.getFollowedSubastasByUser(id).enqueue(object : Callback<List<SubastaSummary>> {
            override fun onResponse(call: Call<List<SubastaSummary>>, response: Response<List<SubastaSummary>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Lista vacía")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<SubastaSummary>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener una subasta por ID
    fun getSubastaInfoById(id: Int, onSuccess: (SubastaInfo) -> Unit, onError: (String) -> Unit) {
        apiService.getSubastaInfoById(id).enqueue(object : Callback<SubastaInfo> {
            override fun onResponse(call: Call<SubastaInfo>, response: Response<SubastaInfo>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Subasta no encontrada")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SubastaInfo>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Obtener una subasta por ID
    fun getHistorialPujas(id: Int, onSuccess: (List<PujaInfo>) -> Unit, onError: (String) -> Unit) {
        apiService.getHistorialBySubastaId(id).enqueue(object : Callback<List<PujaInfo>> {
            override fun onResponse(call: Call<List<PujaInfo>>, response: Response<List<PujaInfo>>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Historial no encontrado")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<PujaInfo>>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Crear una nueva subasta
    fun createSubasta(
        subasta: SubastaInput,
        onSuccess: (SubastaInput) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.createSubasta(subasta).enqueue(object : Callback<SubastaInput> {
            override fun onResponse(call: Call<SubastaInput>, response: Response<SubastaInput>) {
                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) } ?: onError("Error al crear subasta")
                } else {
                    onError("Error código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<SubastaInput>, t: Throwable) {
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
        puja: PujaInput,
        onSuccess: (PujaInput) -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.addPuja(subastaId, puja).enqueue(object : Callback<PujaInput> {
            override fun onResponse(call: Call<PujaInput>, response: Response<PujaInput>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("Respuesta vacía del servidor")
                } else {
                    val errorBodyStr = response.errorBody()?.string()
                    val errorMessage = if (!errorBodyStr.isNullOrEmpty()) {
                        try {
                            val json = JSONObject(errorBodyStr)
                            json.getString("message")
                        } catch (e: Exception) {
                            "Error del servidor (${response.code()})"
                        }
                    } else {
                        "Error inesperado (${response.code()})"
                    }

                    onError(errorMessage)
                }
            }

            override fun onFailure(call: Call<PujaInput>, t: Throwable) {
                onError(t.message ?: "Error desconocido")
            }
        })
    }

    // Adjudicar la subasta al mejor postor
    fun adjudicarSubasta(
        subastaId: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        apiService.adjudicarSubasta(subastaId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error al adjudicar la subasta. Código: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onError("Error en la conexión: ${t.message}")
            }
        })
    }
}
