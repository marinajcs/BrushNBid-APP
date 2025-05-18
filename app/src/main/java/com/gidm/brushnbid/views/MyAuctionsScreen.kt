package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun MyAuctionsScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("activas") }

    val subastasActivas = listOf(
        "Serigrafía floral" to "Paula Butrón",
        "Escultura jónica" to "María de la O",
        "Foto de Aida" to "Aida Soto",
        "Foto de Bulbasaur shiny" to "Carlota de la Vega"
    )

    val subastasFinalizadas = listOf(
        "Pintura de Doraemon" to "Paula Butrón",
        "Jarrón de vaquilla" to "María de la O",
        "Cerámica chulita" to "Aida Soto"
    )

    val subastas = when (selectedTab) {
        "activas" -> subastasActivas
        "finalizadas" -> subastasFinalizadas
        else -> emptyList()
    }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.AUCTIONS,
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
                .background(colorResource(R.color.app_background))

        ) {
            HeaderImage(R.drawable.mis_subastas, "Mis", "Subastas", "")

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilterButton("Subastas activas", selectedTab == "activas") { selectedTab = "activas" }
                FilterButton("Subastas Finalizadas", selectedTab == "finalizadas") { selectedTab = "finalizadas" }
            }

            Column(modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp)
            ) {
                subastas.forEachIndexed { index, (title, author) ->
                    SubastaListItem("", title, author, isFinalizada = selectedTab == "finalizadas")

                    if (index != subastas.lastIndex) {
                        HorizontalDivider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 60.dp),
                            thickness = 1.dp,
                            color = Color.LightGray
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SubastaListItem(img: String, title: String, author: String, isFinalizada: Boolean = false) {
    val grayScaleFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })

    val authorColor = if (isFinalizada) Color.Gray else colorResource(R.color.main_color)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.print_art),
            contentDescription = null,
            colorFilter = if (isFinalizada) grayScaleFilter else null,
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(text = title, fontWeight = FontWeight.Bold)
            Text(text = author, color = authorColor)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MyAuctionsScreenPreview() {
    val navController = rememberNavController()
    MyAuctionsScreen(navController)
}
