package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.R
import com.gidm.brushnbid.data.Estado
import com.gidm.brushnbid.data.ObraSummary
import com.gidm.brushnbid.data.UserPreferences
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import com.gidm.brushnbid.controllers.ObraController
import java.io.File

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var obras by remember { mutableStateOf(listOf<ObraSummary>()) }
    val obraController = remember { ObraController() }

    LaunchedEffect(Unit) {
        username = userPrefs.getUsername() ?: "Username"
        fullname = userPrefs.getFullname() ?: "Nombre completo"
        val userId = userPrefs.getUserId()
        if (userId != null) {
            obraController.getObrasByUser(userId,
                onSuccess = { listaObras ->
                    obras = listaObras.map { obra ->
                        val estado =
                            if (obra.autoriaId == userId && obra.propiedadId != userId)
                                Estado.VENDIDA
                            else
                                Estado.ACTIVA
                        ObraSummary(
                            id = obra.id,
                            titulo = obra.titulo,
                            estado = estado,
                            imagen = obra.imagen
                        )
                    }
                },
                onError = {
                    obras = emptyList()
                }
            )
        }
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
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(R.color.app_background))
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                HeaderProfile(
                    colorResource(R.color.light_blue),
                    R.drawable.profile_pic,
                    username,
                    fullname
                )

                Spacer(modifier = Modifier.padding(9.dp))

                Text(
                    text = "Mis obras",
                    fontSize = 20.sp,
                    lineHeight = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 25.dp)
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 100.dp)
                ) {
                    items(obras) { obra ->
                        ObraCard(obra, navController)
                    }
                }
            }

            IconButton(
                onClick = { navController.navigate("configProfile") },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Configuración",
                    tint = colorResource(id = R.color.dark_gray)
                )
            }
        }
    }
}

@Composable
fun ObraCard(obra: ObraSummary, navController: NavController) {
    val grayScaleFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) })

    Column(
        modifier = Modifier
            .width(160.dp)
    ) {
        AsyncImage(
            model = File(obra.imagen),
            contentDescription = "Obra",
            modifier = Modifier
                .height(190.dp)
                .clip(MaterialTheme.shapes.medium)
                .clickable {
                    navController.navigate("infoObra/${obra.id}")
                },
            contentScale = ContentScale.Fit,
            colorFilter = if (obra.estado == Estado.VENDIDA) grayScaleFilter else null
        )
        Text(
            text = obra.titulo,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            modifier = Modifier.padding(all = 2.dp)
        )
        Text(
            text = obra.estado.name,
            fontSize = 12.sp,
            color = if (obra.estado == Estado.VENDIDA) Color.Gray else colorResource(R.color.main_color)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val navController = rememberNavController()
    ProfileScreen(navController)
}