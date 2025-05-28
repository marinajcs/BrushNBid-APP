package com.gidm.brushnbid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R

@Composable
fun AddObraScreen(
    navController: NavController,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var autoria by remember { mutableStateOf("") }
    var propiedad by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { onBack() }
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Añadir obra",
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .size(160.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colorResource(id = R.color.main_color))
                    .clickable { /* lógica para seleccionar imagen */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.CameraAlt,
                    contentDescription = "Seleccionar imagen",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            AddObraInputField(value = titulo, onValueChange = { titulo = it }, label = "Título")
            AddObraInputField(value = autoria, onValueChange = { autoria = it }, label = "Autoría")
            AddObraInputField(value = propiedad, onValueChange = { propiedad = it }, label = "Propiedad")
            AddObraInputField(value = tipo, onValueChange = { tipo = it }, label = "Tipo")
            AddObraInputField(value = descripcion, onValueChange = { descripcion = it }, label = "Descripción", height = 100.dp)

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Añadir", color = Color.White)
            }
        }
    }
}

@Composable
fun AddObraInputField(value: String, onValueChange: (String) -> Unit, label: String, height: Dp = 55.dp) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .height(height),
            shape = RoundedCornerShape(12.dp),
            singleLine = height <= 55.dp,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.main_color),
                focusedBorderColor = colorResource(id = R.color.main_color),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Gray
            )
        )
    }
}



@Preview(showBackground = true)
@Composable
fun AddObraScreenPreview() {
    val navController = rememberNavController()
    AddObraScreen(
        navController = navController,
        onBack = {},
        onSubmit = {}
    )
}