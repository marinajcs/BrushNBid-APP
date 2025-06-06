package com.gidm.brushnbid.views

import android.util.Log
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
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.gidm.brushnbid.controllers.SubastaController
import com.gidm.brushnbid.data.SubastaInput
import com.gidm.brushnbid.data.UserPreferences
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun AddSubastaScreen(
    navController: NavController,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val subastaController = remember { SubastaController() }
    var userId by remember { mutableStateOf<Int?>(null) }

    val selectedObraIdFlow = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<Int?>("obraId", null)

    val selectedObraId by selectedObraIdFlow?.collectAsState() ?: remember { mutableStateOf(null) }

    val selectedObraTituloFlow = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow<String?>("obraTitulo", null)

    val selectedObraTitulo by selectedObraTituloFlow?.collectAsState() ?: remember { mutableStateOf(null) }

    var precioInicial by remember { mutableStateOf<Double?>(null) }
    var incremento by remember { mutableStateOf<Double?>(null) }
    var precioReserva by remember { mutableStateOf<Double?>(null) }
    var compraInmediata by remember { mutableStateOf<Double?>(null) }
    var duracionDias by remember { mutableStateOf<Int?>(null) }

        val currentDate = remember {
        LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
    }

    LaunchedEffect(Unit) {
        userId = userPrefs.getUserId()
    }

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
                    text = selectedObraTitulo ?: "Seleccionar obra",
                    color = Color.Black,
                    fontSize = 16.sp,
                    lineHeight = 18.sp
                )
                Icon(Icons.AutoMirrored.Filled.ArrowRight, contentDescription = null, tint = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Detalles de la subasta (opcionales)",
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = colorResource(R.color.main_color),
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))
        InputOutlinedField("Precio inicial", precioInicial) { precioInicial = it }
        InputOutlinedField("Incremento", incremento) { incremento = it }
        InputOutlinedField("Precio de reserva", precioReserva) { precioReserva = it }
        InputOutlinedField("Precio de compra inmediata", compraInmediata) { compraInmediata = it }

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
                        .background(colorResource(R.color.main_color))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = currentDate,
                        color = Color.White,
                        fontSize = 14.sp,
                        lineHeight = 16.sp
                    )
                }
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Duración (días)",
                    fontSize = 14.sp,
                    lineHeight = 16.sp,
                    color = colorResource(R.color.main_color),
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "–",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .clickable {
                                duracionDias = when {
                                    duracionDias == null || duracionDias!! <= 1 -> null
                                    else -> duracionDias!! - 1
                                }
                            }
                            .padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (duracionDias == null || duracionDias == 0) "---" else duracionDias.toString(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.width(40.dp),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "+",
                        fontSize = 20.sp,
                        modifier = Modifier
                            .clickable {
                                duracionDias = when {
                                    duracionDias == null || duracionDias == 0 -> 1
                                    else -> duracionDias!! + 1
                                }
                            }
                            .padding(horizontal = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        val duracionInput : String? = if (duracionDias == null || duracionDias == 0){
            null
        } else {
            "$duracionDias days"
        }

        Button(
            onClick = {
                val input = SubastaInput(
                    obraId = selectedObraId ?: return@Button,
                    vendedorId = userId ?: return@Button,
                    precioInicial = precioInicial,
                    incremento = incremento,
                    precioReserva = precioReserva,
                    compraInmediata = compraInmediata,
                    duracion = duracionInput
                )

                subastaController.createSubasta(
                    subasta = input,
                    onSuccess = {
                        Toast.makeText(context, "Subasta creada con éxito", Toast.LENGTH_SHORT).show()
                        onSubmit()
                    },
                    onError = { errorMessage ->
                        if (errorMessage.contains("Expected a string but was BEGIN_OBJECT")) {
                            Toast.makeText(context, "Subasta creada con éxito", Toast.LENGTH_SHORT).show()
                            onSubmit()
                        } else {
                            Toast.makeText(context, "Error al crear subasta: $errorMessage", Toast.LENGTH_LONG).show()
                            Log.e("CreateSubastaError", "Error al crear la subasta: $errorMessage")  // Registro del error en Logcat
                        }
                    }
                )
            },
            enabled = selectedObraId != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selectedObraId != null) Color.Black else colorResource(id = R.color.dark_gray)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Añadir",
                color = if (selectedObraId != null) Color.White else Color.Black
            )
        }
    }
}

@Composable
fun InputOutlinedField(label: String, value: Double?, onValueChange: (Double?) -> Unit) {
    OutlinedTextField(
        value = value?.toString() ?: "",
        onValueChange = { newValue ->
            if (newValue.matches(Regex("^\\d{0,7}(\\.\\d{0,2})?$"))) {
                onValueChange(newValue.toDoubleOrNull())
            }
        },
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
