package com.gidm.brushnbid.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.gidm.brushnbid.data.UserPreferences
import androidx.compose.ui.platform.LocalContext
import com.gidm.brushnbid.views.AddMenuScreen
import com.gidm.brushnbid.views.AddSubastaScreen
import com.gidm.brushnbid.views.ConfigPerfilScreen
import com.gidm.brushnbid.views.FirstStartScreen
import com.gidm.brushnbid.views.MainLoginScreen
import com.gidm.brushnbid.views.RegisterScreen
import com.gidm.brushnbid.views.LoginScreen
import com.gidm.brushnbid.views.MyAuctionsScreen
import com.gidm.brushnbid.views.NotificationScreen
import com.gidm.brushnbid.views.ProfileScreen
import com.gidm.brushnbid.views.SelectObraScreen
import com.gidm.brushnbid.views.SubastasMainScreen
import kotlinx.coroutines.launch

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // Estado para controlar si es la primera vez que se abre la app
    val isFirstTime = remember { mutableStateOf<Boolean?>(null) }

    // Cargar el estado de "es la primera vez" al iniciar
    LaunchedEffect(Unit) {
        isFirstTime.value = UserPreferences(context).isFirstLaunch()
    }

    if (isFirstTime.value == null) {
        return
    }

    NavHost(navController = navController, startDestination = if (isFirstTime.value == true) "firstStart" else "mainLogin") {

        // Pantalla de primer inicio
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

        // Pantalla de login principal
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

        // Pantalla de registro
        composable("register") {
            RegisterScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {
                    navController.navigate("login")
                }
            )
        }

        // Pantalla de login
        composable("login") {
            LoginScreen(
                onBack = {
                    navController.popBackStack()
                },
                onSuccessLogin = {
                    navController.navigate("home")
                },
                onForgotPassword = {

                }
            )
        }

        composable("home") {
            SubastasMainScreen(navController)
        }

        composable("auctions") {
            MyAuctionsScreen(navController)
        }

        composable("notifications") {
            NotificationScreen(navController)
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("configProfile") {
            ConfigPerfilScreen(
                onBack = {
                    navController.popBackStack()
                },
                navController = navController,
                onLogout = {
                    navController.navigate("mainLogin")
                }
            )
        }

        composable("addMenu") {
            AddMenuScreen(navController)
        }

        composable("addSubasta") {
            AddSubastaScreen (
                navController = navController,
                onBack = {
                    navController.popBackStack()
                },
                onSubmit = {
                    navController.navigate("auctions")
                }
            )
        }

        composable("selectObra") {
            SelectObraScreen(
                onBack = { navController.popBackStack() },
                onObraSelected = { obra ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("obraSeleccionada", obra)
                    navController.popBackStack()
                }
            )
        }
    }
}
