package com.gidm.brushnbid

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.gidm.brushnbid.data.UserPreferences
import com.gidm.brushnbid.navigation.AppNavigation

@Composable
fun BrushNBidApp() {
    val context = LocalContext.current
    var startDestination by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        val isFirstTime = UserPreferences(context).isFirstLaunch()
        startDestination = if (isFirstTime) "firstStart" else "login"
    }

    startDestination?.let {
        val navController = rememberNavController()
        AppNavigation(navController = navController, startDestination = it)
    }
}
