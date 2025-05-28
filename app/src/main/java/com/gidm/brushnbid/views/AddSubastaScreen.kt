package com.gidm.brushnbid.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun AddSubastaScreen(
    navController: NavController,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    val selectedObra by navController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<Int?>("obraId", null)
        ?.collectAsState() ?: remember { mutableStateOf(null) }

    var precioInicial by remember { mutableStateOf("") }
    var incremento by remember { mutableStateOf("") }
    var precioReserva by remember { mutableStateOf("") }
    var compraInmediata by remember { mutableStateOf("") }
    var duracion by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.app_background))
            .padding(24.dp)
    ) {
        // Icono de volver y título
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
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Añadir subasta",
                fontSize = 20.sp,
                lineHeight = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Obra a subastar",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = colorResource(R.color.main_color),
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(12.dp))

        // Selector de obra
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .border(1.dp, colorResource(R.color.main_color), RoundedCornerShape(12.dp))
                .clickable { navController.navigate("selectObra") }
                .padding(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(
                    //text = selectedObra?.value?.titulo ?: "Seleccionar obra",
                    text = "Seleccionar obra",
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )
                Icon(Icons.AutoMirrored.Filled.ArrowRight, contentDescription = null, tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Detalles de la subasta",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = colorResource(R.color.main_color),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        InputOutlinedField("Precio inicial", precioInicial) { precioInicial = it }
        InputOutlinedField("Incremento", incremento) { incremento = it }
        InputOutlinedField("Precio reserva", precioReserva) { precioReserva = it }
        InputOutlinedField("Compra inmediata", compraInmediata) { compraInmediata = it }

        Spacer(modifier = Modifier.height(24.dp))

        // Campos de fecha y duración
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Fecha de inicio",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = colorResource(R.color.main_color),
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text("18/04/2025", color = colorResource(R.color.main_color))
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Duración",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = colorResource(R.color.main_color),
                    fontWeight = FontWeight.Medium
                )
                Box(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(colorResource(R.color.main_color))
                        .padding(12.dp)
                ) {
                    Text("00/00/0000", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val allFieldsFilled = precioInicial.isNotBlank()
                // && obra.isNotBlank()
                // && incremento.isNotBlank()
                //&& precioReserva.isNotBlank()
                //&& compraInmediata.isNotBlank()
                //&& duracion.isNotBlank()

        Button(
            onClick = onSubmit,
            enabled = allFieldsFilled,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (allFieldsFilled) Color.Black else colorResource(id = R.color.dark_gray)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Añadir",
                color = if (allFieldsFilled) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun InputOutlinedField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(label, color = Color.Black) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(R.color.main_color),
            unfocusedBorderColor = colorResource(R.color.main_color),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = colorResource(R.color.main_color)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun AddSubastaScreenPreview() {
    val navController = rememberNavController()
    AddSubastaScreen(
        navController = navController,
        onBack = {},
        onSubmit = {}
    )
}
