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
import com.gidm.brushnbid.views.MainLoginScreen
import com.gidm.brushnbid.views.RegisterScreen
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
                        navController.navigate("mainLogin") {
                            popUpTo("firstStart") { inclusive = true }
                        }
                    }
                }
            )
        }

        composable("mainLogin") {
            MainLoginScreen(
                onCreateAccount = {
                    navController.navigate("register") // Navegar a registro
                },
                onLogin = {
                    navController.navigate("login")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {

                }
            )
        }

        composable("login") {
            LoginScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {

                },
                onForgotPassword = {

                }
            )
        }

    }
}
