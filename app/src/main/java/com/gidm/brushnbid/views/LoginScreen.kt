package com.gidm.brushnbid.views

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
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun LoginScreen(
    onBack: () -> Unit,
    onSubmit: () -> Unit,
    onForgotPassword: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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

        InputField("Dirección de e-mail", email) { email = it }
        InputField("Contraseña", password, isPassword = true) { password = it }

        val allFieldsFilled = email.isNotBlank() && password.isNotBlank()

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
            onClick = onSubmit,
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
        onSubmit = {},
        onForgotPassword = {}
    )
}
