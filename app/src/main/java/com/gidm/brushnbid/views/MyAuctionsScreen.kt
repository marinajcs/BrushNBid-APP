package com.gidm.brushnbid.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.navigation.BottomNavBar
import com.gidm.brushnbid.navigation.BottomNavItem

@Composable
fun MyAuctionsScreen(navController: NavController) {

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
        Box(modifier = Modifier.padding(innerPadding)) {
            Text("Pantalla de mis subastas")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAuctionsScreenPreview(){
    val navController = rememberNavController()
    MyAuctionsScreen(navController)
}