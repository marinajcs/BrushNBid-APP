package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import java.io.File
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gidm.brushnbid.R
import com.gidm.brushnbid.controllers.ObraController
import com.gidm.brushnbid.data.ObraInfo
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun InfoObraScreen(
    obraId: Int,
    onBack: () -> Unit,
    navController: NavController
) {
    val context = LocalContext.current
    val obraController = remember { ObraController() }
    var obra by remember { mutableStateOf<ObraInfo?>(null) }

    LaunchedEffect(obraId) {
        obraController.getObraInfoById(
            obraId,
            onSuccess = { obra = it },
            onError = { /* Manejo de error */ }
        )
    }


    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.PROFILE,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { navController.navigate("addMenu") },
                onNotificationsClick = { navController.navigate("notifications") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background))
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                modifier = Modifier
                    .size(28.dp)
                    .clickable { onBack() }
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (obra?.imagen?.isNotBlank() == true && File(obra!!.imagen).exists()) {
                AsyncImage(
                    model = File(obra!!.imagen),
                    contentDescription = "Imagen de obra",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = obra?.titulo ?: "Título de la obra",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                color = Color.Black
            )

            Text(
                text = obra?.autoria ?: "Autoría",
                fontSize = 15.sp,
                lineHeight = 17.sp,
                color = colorResource(id = R.color.main_color)
            )

            Spacer(modifier = Modifier.height(24.dp))

            InfoBox("Propiedad", obra?.propiedad ?: "Propiedad")
            Spacer(modifier = Modifier.height(12.dp))
            InfoBox("Tipo", obra?.tipo ?: "Tipo")
            Spacer(modifier = Modifier.height(12.dp))
            InfoBox("Descripción", obra?.descripcion ?: "Descripción", height = 100.dp)
        }
    }
}

@Composable
fun InfoBox(label: String, value: String, height: Dp = 55.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.main_color),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            lineHeight = 13.sp,
            color = colorResource(id = R.color.main_color)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoObraScreenPreview() {
    val navController = rememberNavController()
    InfoObraScreen(
        1,
        onBack = {},
        navController
    )
}
