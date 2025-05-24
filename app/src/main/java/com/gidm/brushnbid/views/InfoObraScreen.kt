package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
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
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.controllers.ObraController
import com.gidm.brushnbid.data.Obra
import com.gidm.brushnbid.data.ObraSummary
import com.gidm.brushnbid.data.UserPreferences
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
    /*
    var obra by remember { mutableStateOf<Obra>(

        value = TODO(),
        policy = TODO()
    ) }

    LaunchedEffect(obraId) {
        obraController.getObraById(
            obraId,
            onSuccess = { obra = it },
            onError = { }
        )
    }
    */

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.PROFILE,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { /* lógica para crear */ },
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

            Image(
                painter = painterResource(id = R.drawable.print_art),
                contentDescription = "Imagen de obra",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Título de la obra",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp,
                color = Color.Black
            )

            Text(
                text = "Autoría",
                fontSize = 15.sp,
                lineHeight = 17.sp,
                color = colorResource(id = R.color.main_color)
            )

            Spacer(modifier = Modifier.height(24.dp))

            InfoBox("Propiedad")
            Spacer(modifier = Modifier.height(12.dp))
            InfoBox("Tipo")
            Spacer(modifier = Modifier.height(12.dp))
            InfoBox("Descripción", height = 100.dp)
        }
    }
}

@Composable
fun InfoBox(label: String, height: Dp = 50.dp) {
    Box(
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
            color = Color.Black,
            fontSize = 14.sp
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
