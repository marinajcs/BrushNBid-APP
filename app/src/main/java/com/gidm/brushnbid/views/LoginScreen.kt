package com.gidm.brushnbid.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gidm.brushnbid.R
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.gidm.brushnbid.api.ApiClient
import com.gidm.brushnbid.api.ApiService
import com.gidm.brushnbid.data.LoginRequest
import com.gidm.brushnbid.data.LoginResponse
import com.gidm.brushnbid.data.UserPreferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onSuccessLogin: (String) -> Unit,
    onForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val apiService = remember { ApiClient.retrofit.create(ApiService::class.java) }
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
            .padding(24.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Volver",
            modifier = Modifier
                .size(28.dp)
                .clickable { onBack() }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Tu próxima obra te espera",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 27.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField("Dirección de e-mail o username", email) { email = it }
        InputField("Contraseña", password, isPassword = true) { password = it }

        val allFieldsFilled = email.isNotBlank() && password.isNotBlank()

        if (!errorMessage.isNullOrBlank()) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "¿Has olvidado tu contraseña?",
            fontSize = 14.sp,
            color = Color.Black,
            lineHeight = 17.sp,
            modifier = Modifier
                .clickable { onForgotPassword() }
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = {
                val request = LoginRequest(email, password)
                apiService.login(request).enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body?.token != null) {
                                CoroutineScope(Dispatchers.IO).launch {
                                    userPrefs.saveUserSession(body.userId, body.token, body.username, body.email, body.fullname)
                                }
                                onSuccessLogin(body.token)
                            }
                        } else {
                            val errorBody = response.errorBody()?.string()
                            errorMessage = parseErrorMessage(errorBody)
                        }
                    }

                    fun parseErrorMessage(errorBody: String?): String {
                        return try {
                            if (errorBody == null) "Error desconocido"
                            else {
                                val json = JSONObject(errorBody)
                                json.optString("message", "Error desconocido")
                            }
                        } catch (e: Exception) {
                            "Error desconocido"
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        errorMessage = "Error de red: ${t.message}"
                        Log.e("LoginScreen", "Login failed", t)
                    }
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = allFieldsFilled,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (allFieldsFilled) Color.Black else colorResource(id = R.color.dark_gray)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Iniciar sesión",
                color = if (allFieldsFilled) Color.White else Color.Black
            )
        }
    }
}

fun onForgotPassword() {
    TODO("Not yet implemented")
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onBack = {},
        onSuccessLogin = {},
        onForgotPassword = {}
    )
}
