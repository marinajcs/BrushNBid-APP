package com.gidm.brushnbid.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.data.UserPreferences
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ConfigProfileScreen(
    onBack: () -> Unit,
    navController: NavController,
    onLogout: () -> Unit
) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val coroutineScope = rememberCoroutineScope()

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

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Configuración del perfil",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            lineHeight = 27.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Opciones de configuración
        ConfigOption(text = "Editar perfil") {
            //navController.navigate("")
        }
        HorizontalDivider(color = colorResource(id = R.color.main_color), thickness = 1.4.dp)

        ConfigOption(text = "Notificaciones") {
            // Navegar a notificaciones
        }
        HorizontalDivider(color = colorResource(id = R.color.main_color), thickness = 1.dp)

        ConfigOption(text = "Historial de subastas") {
            // Navegar a historial subastas
        }

        Spacer(modifier = Modifier.weight(0.95f))

        // Botón cerrar sesión
        OutlinedButton(
            onClick = {
                coroutineScope.launch {
                    userPrefs.clearUserSession()
                    onLogout()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = colorResource(id = R.color.main_color)),
            border = BorderStroke(1.dp, colorResource(id = R.color.main_color))
        ) {
            Text(
                text = "Cerrar sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ConfigOption(text: String, onClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 19.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowRight,
                contentDescription = "Ir a $text",
                tint = colorResource(id = R.color.main_color)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConfigPerfilScreenPreview() {
    val navController = rememberNavController()
    ConfigProfileScreen(
        onBack = {},
        navController,
        onLogout = {}
    )
}