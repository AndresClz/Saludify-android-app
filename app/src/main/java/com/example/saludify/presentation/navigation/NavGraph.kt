package com.example.saludify.presentation.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.saludify.presentation.screens.home.HomeScreen
import com.example.saludify.presentation.screens.login.LoginScreen
import com.example.saludify.presentation.screens.profile.ProfileScreen
import com.example.saludify.presentation.screens.main.MainScreen
@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Login.route
    ) {

        composable(Routes.Login.route) {
            LoginScreen(

                onLoginSuccess = {

                    navController.navigate(
                        Routes.Main.route
                    )

                }
            )
        }

        composable(Routes.Main.route) {
            MainScreen()
        }

        composable(Routes.Profile.route) {
            ProfileScreen()
        }
    }
}