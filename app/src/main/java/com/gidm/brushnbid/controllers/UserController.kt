package com.gidm.brushnbid.controllers

import com.gidm.brushnbid.api.ApiClient
import com.gidm.brushnbid.data.User
import com.gidm.brushnbid.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserController() {

    private val apiService = ApiClient.retrofit.create(ApiService::class.java)

    // Obtener todos los usuarios
    fun getUsers(onSuccess: (List<User>) -> Unit, onFailure: (String) -> Unit) {
        apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    onSuccess(users)
                } else {
                    onFailure("Error al obtener usuarios: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

    // Obtener un usuario por ID
    fun getUserById(id: Int, onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        apiService.getUserById(id).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    if (user != null) {
                        onSuccess(user)
                    } else {
                        onFailure("Usuario no encontrado")
                    }
                } else {
                    onFailure("Error al obtener el usuario: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

    // Crear un nuevo usuario
    fun createUser(user: User, onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        apiService.createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val createdUser = response.body()
                    if (createdUser != null) {
                        onSuccess(createdUser)
                    } else {
                        onFailure("Error al crear el usuario")
                    }
                } else {
                    onFailure("Error al crear el usuario: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

    // Actualizar un usuario existente
    fun updateUser(id: Int, user: User, onSuccess: (User) -> Unit, onFailure: (String) -> Unit) {
        apiService.updateUser(id, user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val updatedUser = response.body()
                    if (updatedUser != null) {
                        onSuccess(updatedUser)
                    } else {
                        onFailure("Error al actualizar el usuario")
                    }
                } else {
                    onFailure("Error al actualizar el usuario: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

    // Eliminar un usuario
    fun deleteUser(id: Int, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        apiService.deleteUser(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure("Error al eliminar el usuario: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

}
