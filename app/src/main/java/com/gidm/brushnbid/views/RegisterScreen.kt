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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

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
            text = "Únete a BrushNbid",
            fontSize = 24.sp,
            lineHeight = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField("Nombre y apellidos", name) { name = it }
        InputField("Dirección de e-mail", email) { email = it }
        InputField("Contraseña", password, isPassword = true) { password = it }

        Text(
            text = "La contraseña debe tener al menos 8 caracteres.",
            fontSize = 12.sp,
            lineHeight = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        InputField("País", country) { country = it }
        InputField("Dirección", address) { address = it }

        val allFieldsFilled = name.isNotBlank()
                && email.isNotBlank()
                && password.isNotBlank()
                && country.isNotBlank()
                && address.isNotBlank()

        Spacer(modifier = Modifier.weight(1f))

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
                text = "Subasta tu arte",
                color = if (allFieldsFilled) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    value: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = colorResource(id = R.color.main_light_color),
            unfocusedContainerColor = colorResource(id = R.color.main_light_color),
            focusedBorderColor = colorResource(id = R.color.main_light_color),
            unfocusedBorderColor = colorResource(id = R.color.main_light_color),
            focusedLabelColor = colorResource(id = R.color.main_color),
            unfocusedLabelColor = colorResource(id = R.color.main_color),
            cursorColor = Color.Black,
            focusedTextColor = colorResource(id = R.color.main_color),
            unfocusedTextColor = colorResource(id = R.color.main_color)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onBack = {},
        onSubmit = {}
    )
}
