package com.gidm.brushnbid.views

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.controllers.ObraController
import com.gidm.brushnbid.data.ObraInput
import com.gidm.brushnbid.data.UserPreferences
import java.io.File

@Composable
fun AddObraScreen(
    navController: NavController,
    onBack: () -> Unit,
    onSubmit: () -> Unit
) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val obraController = remember { ObraController() }
    val imgid = R.drawable.print_art

    var titulo by remember { mutableStateOf("") }
    var tipo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf<Int?>(null) }
    var obrasDir by remember { mutableStateOf<File?>(null) }

    LaunchedEffect(Unit) {
        userId = userPrefs.getUserId()
        obrasDir = obraController.getObraImageDir(context)
    }

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
            SelectTipoField(selected = tipo, onSelectedChange = { tipo = it })
            AddObraInputField(value = descripcion, onValueChange = { descripcion = it }, label = "Descripción", height = 120.dp)

            Spacer(modifier = Modifier.weight(1f))

            val allFieldsFilled = userId != null
                    && titulo.isNotBlank()
                    && tipo.isNotBlank()

            Button(
                onClick = {
                    val newObra = ObraInput(
                        titulo = titulo,
                        autoriaId = userId!!,
                        propiedadId = userId!!,
                        tipo = tipo,
                        descripcion = descripcion//,
                        //imagen = ""
                    )
                    obraController.createObra(
                        obra = newObra,
                        onSuccess = {
                            Toast.makeText(context, "Obra creada con éxito", Toast.LENGTH_SHORT).show()
                            onSubmit()
                        },
                        onError = { errorMsg ->
                            Toast.makeText(context, "Error: $errorMsg", Toast.LENGTH_LONG).show()
                        }
                    )
                },
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
}

@Composable
fun AddObraInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    height: Dp = 55.dp
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = height),
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectTipoField(
    selected: String,
    onSelectedChange: (String) -> Unit,
    label: String = "Tipo"
) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("escultura", "pintura", "fotografía", "otras")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        OutlinedTextField(
            value = selected,
            onValueChange = { onSelectedChange(it) },
            readOnly = true,
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp), // en lugar de .height(55.dp)
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = colorResource(id = R.color.main_color),
                focusedBorderColor = colorResource(id = R.color.main_color),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = Color.Gray
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onSelectedChange(option)
                        expanded = false
                    }
                )
            }
        }
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