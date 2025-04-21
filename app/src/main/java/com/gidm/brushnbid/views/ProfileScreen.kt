package com.gidm.brushnbid.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun ProfileScreen(navController: NavController) {
    var selectedItem by remember { mutableStateOf(BottomNavItem.NOTIFICATIONS) }

    Scaffold(
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
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("Pantalla de perfil")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    val navController = rememberNavController()
    ProfileScreen(navController)
}