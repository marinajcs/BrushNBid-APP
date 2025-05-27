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
import com.gidm.brushnbid.views.ConfigProfileScreen
import com.gidm.brushnbid.views.FirstStartScreen
import com.gidm.brushnbid.views.InfoObraScreen
import com.gidm.brushnbid.views.InfoSubastaScreen
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
    val userPrefs = remember { UserPreferences(context) }
    val scope = rememberCoroutineScope()

    // Estado para controlar si es la primera vez que se abre la app
    val isFirstTime = remember { mutableStateOf<Boolean?>(null) }
    val hasToken = remember { mutableStateOf<String?>(null) }

    // Cargar el estado de "es la primera vez" al iniciar
    LaunchedEffect(Unit) {
        isFirstTime.value = UserPreferences(context).isFirstLaunch()
        hasToken.value = userPrefs.getToken()
    }

    if (isFirstTime.value == null) {
        return
    }

    val start = if (isFirstTime.value == true) {
        "firstStart"
    } else if (hasToken.value == null) {
        "mainLogin"
    } else {
        "home"
    }

    NavHost(navController = navController, startDestination = start) {

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
                    navController.navigate("mainLogin")
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
            ConfigProfileScreen(
                onBack = {
                    navController.popBackStack()
                },
                navController = navController,
                onLogout = {
                    navController.navigate("mainLogin")
                }
            )
        }

        composable("infoObra/{obraId}") { backStackEntry ->
            val obraId = backStackEntry.arguments?.getString("obraId")?.toIntOrNull()

            if (obraId != null) {
                InfoObraScreen(
                    obraId = obraId,
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }
        }

        composable("infoSubasta/{subastaId}") { backStackEntry ->
            val subastaId = backStackEntry.arguments?.getString("subastaId")?.toIntOrNull()

            if (subastaId != null) {
                InfoSubastaScreen(
                    subastaId = subastaId,
                    onBack = { navController.popBackStack() },
                    navController = navController
                )
            }
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
