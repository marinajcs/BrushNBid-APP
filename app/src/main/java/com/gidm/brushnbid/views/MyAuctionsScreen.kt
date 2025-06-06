package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.gidm.brushnbid.R
import com.gidm.brushnbid.controllers.SubastaController
import com.gidm.brushnbid.data.SubastaSummary
import com.gidm.brushnbid.data.UserPreferences
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem
import java.io.File

@Composable
fun MyAuctionsScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    var selectedTab by remember { mutableStateOf("activas") }
    var subastas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    var subastasActivas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    var subastasFinalizadas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    val subastaController = remember { SubastaController() }

    LaunchedEffect(Unit) {
        val userId = userPrefs.getUserId()
        if (userId != null) {
            subastaController.getActiveSubastasByUser(
                id = userId,
                onSuccess = { activas ->
                    val activasResumen = activas.map {
                        SubastaSummary(
                            subastaId = it.subastaId,
                            obra = it.obra,
                            vendedor = it.vendedor,
                            image = it.image
                        )
                    }
                    subastasActivas = activasResumen
                    if (selectedTab == "activas") subastas = activasResumen
                },
                onError = {
                    subastasActivas = emptyList()
                }
            )

            subastaController.getFinishedSubastasByUser(
                id = userId,
                onSuccess = { finalizadas ->
                    val finalizadasResumen = finalizadas.map {
                        SubastaSummary(
                            subastaId = it.subastaId,
                            obra = it.obra,
                            vendedor = it.vendedor,
                            image = it.image
                        )
                    }
                    subastasFinalizadas = finalizadasResumen
                    if (selectedTab == "finalizadas") subastas = finalizadasResumen
                },
                onError = {
                    subastasFinalizadas = emptyList()
                }
            )
        }
    }

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.AUCTIONS,
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
                .background(colorResource(R.color.app_background))

        ) {
            HeaderImage(colorResource(R.color.main_color), R.drawable.mis_subastas, "Mis", "Subastas")

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                FilterButton("Subastas activas", selectedTab == "activas") {
                    selectedTab = "activas"
                    subastas = subastasActivas
                }
                FilterButton("Subastas Finalizadas", selectedTab == "finalizadas") {
                    selectedTab = "finalizadas"
                    subastas = subastasFinalizadas
                }
            }

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                subastas.forEachIndexed { index, subasta ->
                    SubastaListItem(
                        id = subasta.subastaId,
                        img = subasta.image,
                        title = subasta.obra,
                        author = subasta.vendedor,
                        isFinalizada = selectedTab == "finalizadas",
                        navController
                    )

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
fun SubastaListItem(id: Int, img: String, title: String, author: String,
                    isFinalizada: Boolean = false,  navController: NavController) {

    val grayScaleFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })
    val authorColor = if (isFinalizada) Color.Gray else colorResource(R.color.main_color)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate("infoSubasta/${id}")
            }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (img.isNotEmpty() && img.isNotBlank()) {
            AsyncImage(
                model = File(img),
                contentDescription = "Imagen de obra",
                colorFilter = if (isFinalizada) grayScaleFilter else null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.print_art),
                contentDescription = "Imagen por defecto",
                colorFilter = if (isFinalizada) grayScaleFilter else null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
        }

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
