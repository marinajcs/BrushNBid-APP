package com.gidm.brushnbid.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gidm.brushnbid.R

@Composable
fun BottomNavBar(
    selectedItem: BottomNavItem,
    onHomeClick: () -> Unit,
    onAuctionsClick: () -> Unit,
    onCreateClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Box {
        NavigationBar(
            containerColor = colorResource(id = R.color.app_background),
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
        ) {
            NavigationBarItem(
                selected = selectedItem == BottomNavItem.HOME,
                onClick = onHomeClick,
                icon = { Icon(Icons.Default.Home, contentDescription = "Inicio") }
            )
            NavigationBarItem(
                selected = selectedItem == BottomNavItem.AUCTIONS,
                onClick = onAuctionsClick,
                icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Subastas") }
            )
            Spacer(modifier = Modifier.weight(1f)) // espacio para el FAB

            NavigationBarItem(
                selected = selectedItem == BottomNavItem.NOTIFICATIONS,
                onClick = onNotificationsClick,
                icon = { Icon(Icons.Default.Notifications, contentDescription = "Notificaciones") }
            )
            NavigationBarItem(
                selected = selectedItem == BottomNavItem.PROFILE,
                onClick = onProfileClick,
                icon = { Icon(Icons.Default.Person, contentDescription = "Perfil") }
            )
        }

        FloatingActionButton(
            onClick = onCreateClick,
            containerColor = colorResource(id = R.color.main_color),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = -(8.dp))
        ) {
            Icon(Icons.Default.Add, contentDescription = "Crear")
        }
    }
}

enum class BottomNavItem {
    HOME, AUCTIONS, NOTIFICATIONS, PROFILE
}

@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    var selectedItem by remember { mutableStateOf(BottomNavItem.HOME) }

    Scaffold(
        bottomBar = {
            BottomNavBar(
                selectedItem = selectedItem,
                onHomeClick = { selectedItem = BottomNavItem.HOME },
                onAuctionsClick = { selectedItem = BottomNavItem.AUCTIONS },
                onCreateClick = { /* lógica para crear nueva subasta */ },
                onNotificationsClick = { selectedItem = BottomNavItem.NOTIFICATIONS },
                onProfileClick = { selectedItem = BottomNavItem.PROFILE }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedItem) {
                BottomNavItem.HOME -> Text("Pantalla de inicio")
                BottomNavItem.AUCTIONS -> Text("Pantalla de subastas")
                BottomNavItem.NOTIFICATIONS -> Text("Notificaciones")
                BottomNavItem.PROFILE -> Text("Perfil")
            }
        }
    }
}
