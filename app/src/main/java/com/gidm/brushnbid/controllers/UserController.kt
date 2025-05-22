package com.gidm.brushnbid.controllers

import com.gidm.brushnbid.data.User
import com.gidm.brushnbid.api.ApiService
import com.gidm.brushnbid.data.LoginRequest
import com.gidm.brushnbid.data.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserController(private val apiService: ApiService) {

    fun login(username: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        val loginRequest = LoginRequest(username, password)

        apiService.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val token = response.body()?.token
                    if (token != null) {
                        onSuccess(token)  // Puedes guardar el token con DataStore o SharedPreferences
                    } else {
                        onError("Token no recibido")
                    }
                } else {
                    onError("Credenciales incorrectas")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError("Error de red: ${t.message}")
            }
        })
    }

    // Cerrar sesión (Logout)
    fun logout(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        apiService.logout().enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure("Error al cerrar sesión: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                onFailure("Error al hacer la solicitud: ${t.message}")
            }
        })
    }

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
