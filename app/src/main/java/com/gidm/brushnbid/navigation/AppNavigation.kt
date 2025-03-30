package com.gidm.brushnbid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gidm.brushnbid.data.UserPreferences
import androidx.compose.ui.platform.LocalContext
import com.gidm.brushnbid.views.FirstStartScreen
import com.gidm.brushnbid.views.LoginScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = startDestination) {
        composable("firstStart") {
            FirstStartScreen(
                onContinue = {
                    scope.launch {
                        UserPreferences(context).setFirstLaunchDone()
                        navController.navigate("login") {
                            popUpTo("firstStart") { inclusive = true }
                        }
                    }
                }
            )
        }
        composable("login") {
            LoginScreen(
                onCreateAccount = { /* TODO: navegación a registro */ },
                onLogin = { /* TODO: navegación a login form */ }
            )
        }
    }
}
