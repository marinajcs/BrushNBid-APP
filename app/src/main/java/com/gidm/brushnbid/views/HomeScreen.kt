package com.gidm.brushnbid.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.foundation.lazy.*
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.gidm.brushnbid.R
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun SubastasMainScreen() {
    var selectedTab by remember { mutableStateOf("activas") }
    var selectedItem by remember { mutableStateOf(BottomNavItem.HOME) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = selectedItem,
                onHomeClick = { selectedItem = BottomNavItem.HOME },
                onAuctionsClick = { selectedItem = BottomNavItem.AUCTIONS },
                onCreateClick = { /* lógica para crear */ },
                onNotificationsClick = { selectedItem = BottomNavItem.NOTIFICATIONS },
                onProfileClick = { selectedItem = BottomNavItem.PROFILE }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(colorResource(id = R.color.app_background)),
            contentPadding = PaddingValues(bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            item {
                // Buscador
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    placeholder = { Text("Buscar en BrushNbid") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    },
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = colorResource(id = R.color.main_color),
                        unfocusedBorderColor = colorResource(id = R.color.main_color)
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
                    }
                    FilterButton("Subastas seguidas", selected = selectedTab == "seguidas") {
                        selectedTab = "seguidas"
                    }
                }
            }

            // Lista de subastas (simulada)
            items(5) {
                SubastaCard(
                    title = "Serigrafía orca",
                    author = "Paula Butrón",
                    imageUrl = "https://storage.googleapis.com/pod_public/1300/234443.jpg"
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
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ) {
        Text(text = text, color = textColor, fontSize = 14.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun SubastaCard(title: String, author: String, imageUrl: String) {
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
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SubastasMainScreenPreview() {
    SubastasMainScreen()
}
