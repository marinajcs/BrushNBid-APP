package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.gidm.brushnbid.R
import com.gidm.brushnbid.controllers.SubastaController
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem
import com.gidm.brushnbid.data.SubastaSummary
import androidx.compose.ui.platform.LocalContext
import com.gidm.brushnbid.data.UserPreferences

@Composable
fun SubastasMainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf("activas") }
    var subastas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    var subastasActivas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    var subastasSeguidas: List<SubastaSummary> by remember { mutableStateOf(listOf()) }
    val subastaController = remember { SubastaController() }
    var searchText by remember { mutableStateOf("") }

    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }

    val subastasFiltradas = subastas.filter {
        it.obra.contains(searchText, ignoreCase = true) ||
                it.vendedor.contains(searchText, ignoreCase = true)
    }

    LaunchedEffect(Unit) {
        subastaController.getActiveSubastas(
            onSuccess = { listaSubastas ->
                val summaries = listaSubastas.map { subasta ->
                    SubastaSummary(
                        subastaId = subasta.subastaId,
                        obra = subasta.obra,
                        vendedor = subasta.vendedor,
                        image = subasta.image
                    )
                }
                subastasActivas = summaries
                subastas = summaries
            },
            onError = {
                subastasActivas = emptyList()
                subastas = emptyList()
            }
        )

        val userId = userPrefs.getUserId()
        if (userId != null) {
            subastaController.getFollowedSubastasByUser(
                id = userId,
                onSuccess = { listaSeguidas ->
                    subastasSeguidas = listaSeguidas
                },
                onError = {
                    subastasSeguidas = emptyList()
                }
            )
        }
    }

    Scaffold(
        containerColor = colorResource(id = R.color.app_background),
        bottomBar = {
            BottomNavBar(
                selectedItem = BottomNavItem.HOME,
                onHomeClick = { navController.navigate("home") },
                onAuctionsClick = { navController.navigate("auctions") },
                onCreateClick = { navController.navigate("addMenu") },
                onNotificationsClick = { navController.navigate("notifications") },
                onProfileClick = { navController.navigate("profile") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background)),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {

            item {
                // Buscador
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    placeholder = { Text("Buscar en BrushNbid") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.main_light_color),
                        unfocusedContainerColor = colorResource(id = R.color.main_light_color),
                        focusedBorderColor = colorResource(id = R.color.main_color),
                        unfocusedBorderColor = colorResource(id = R.color.main_color),
                        focusedTextColor = colorResource(id = R.color.main_color),
                        unfocusedTextColor = colorResource(id = R.color.main_color),
                        focusedLeadingIconColor = colorResource(id = R.color.main_color),
                        unfocusedLeadingIconColor = colorResource(id = R.color.main_color),
                        focusedPlaceholderColor = colorResource(id = R.color.main_color),
                        unfocusedPlaceholderColor = colorResource(id = R.color.main_color)
                    )
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    FilterButton("Subastas activas", selected = selectedTab == "activas") {
                        selectedTab = "activas"
                        subastas = subastasActivas
                    }
                    FilterButton("Subastas seguidas", selected = selectedTab == "seguidas") {
                        selectedTab = "seguidas"
                        subastas = subastasSeguidas
                    }
                }
            }

            items(subastasFiltradas) { subasta ->
                SubastaCard(
                    id = subasta.subastaId,
                    title = subasta.obra,
                    author = subasta.vendedor,
                    imageUrl = subasta.image,
                    navController
                )
            }
        }
    }
}

@Composable
fun FilterButton(text: String, selected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (selected) colorResource(id = R.color.main_color) else Color.White
    val textColor = if (selected) Color.White else colorResource(id = R.color.main_color)

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(11.dp))
            .border(
                1.dp,
                color = colorResource(R.color.main_color),
                shape = RoundedCornerShape(11.dp)
            )
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SubastaCard(id: Int, title: String, author: String, imageUrl: String, navController: NavController) {
    val isPreview = LocalInspectionMode.current
    val painter = if (isPreview) {
        painterResource(id = R.drawable.print_art) // Usa una imagen local para Preview
    } else {
        rememberAsyncImagePainter(imageUrl)
    }

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Text(
            text = author,
            color = colorResource(id = R.color.main_color),
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Image(
            painter = painter,
            contentDescription = "Obra de arte",
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .height(240.dp)
                .clickable {
                    navController.navigate("infoSubasta/${id}")
                }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubastasMainScreenPreview() {
    val navController = rememberNavController()
    SubastasMainScreen(navController)
}
