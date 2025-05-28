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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.gidm.brushnbid.controllers.UserController
import com.gidm.brushnbid.data.User
import java.util.Locale
import android.widget.Toast

@Composable
fun RegisterScreen(
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    val userController = remember { UserController() }
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    val isPasswordValid = password.length >= 5
    val countries = remember {
        Locale.getISOCountries()
            .map { Locale("", it).getDisplayCountry(Locale("es")) }
            .sorted()
    }

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
        InputField("Nombre de usuario/a", username) { username = it }
        InputField("Dirección de e-mail", email) { email = it }
        InputField("Contraseña", password, isPassword = true) { password = it }

        if (!isPasswordValid) {
            Text(
                text = "La contraseña debe tener al menos 5 caracteres",
                fontSize = 12.sp,
                lineHeight = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }

        AutoCompleteField("País", country, countries) { country = it }
        InputField("Dirección", address) { address = it }

        val allFieldsFilled = name.isNotBlank()
                && username.isNotBlank()
                && email.isNotBlank()
                && password.isNotBlank()
                && country.isNotBlank()
                && address.isNotBlank()

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val newUser = User(
                    fullname = name,
                    username = username,
                    email = email,
                    password = password,
                    direccion = "$country, $address"
                )
                userController.createUser(
                    user = newUser,
                    onSuccess = {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        onSubmit()
                    },
                    onFailure = { errorMsg ->
                        Toast.makeText(context, "Error: $errorMsg", Toast.LENGTH_LONG).show()
                    }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            enabled = allFieldsFilled && isPasswordValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (allFieldsFilled && isPasswordValid) Color.Black else colorResource(id = R.color.dark_gray)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Subasta tu arte",
                color = if (allFieldsFilled && isPasswordValid) Color.White else Color.Black
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
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteField(
    label: String,
    value: String,
    options: List<String>,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val filteredOptions = if (value.isEmpty()) {
        emptyList()
    } else {
        options.filter { it.contains(value, ignoreCase = true) }
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                expanded = true
            },
            label = { Text(label) },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .padding(vertical = 6.dp),
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = colorResource(id = R.color.main_light_color),
                unfocusedContainerColor = colorResource(id = R.color.main_light_color),
                focusedBorderColor = colorResource(id = R.color.main_light_color),
                unfocusedBorderColor = colorResource(id = R.color.main_light_color),
                focusedLabelColor = colorResource(id = R.color.main_color),
                unfocusedLabelColor = colorResource(id = R.color.main_color),
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            )
        )

        ExposedDropdownMenu(
            expanded = expanded && filteredOptions.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            filteredOptions.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption) },
                    onClick = {
                        onValueChange(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onBack = {},
        onSubmit = {}
    )
}

