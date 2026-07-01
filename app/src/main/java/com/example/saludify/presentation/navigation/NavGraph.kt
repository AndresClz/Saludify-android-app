package com.example.saludify.presentation.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.saludify.presentation.screens.confirmed.ConfirmedScreen
import com.example.saludify.presentation.screens.login.LoginScreen
import com.example.saludify.presentation.screens.main.MainScreen
import com.example.saludify.presentation.screens.onboarding.OnboardingScreen
import com.example.saludify.presentation.screens.profile.ProfileScreen
import com.example.saludify.presentation.screens.sacaturno.SacarTurnoScreen
import com.example.saludify.presentation.screens.splash.SplashScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route,
        enterTransition    = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition     = { slideOutHorizontally(targetOffsetX = { -it / 3 }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it / 3 }) },
        popExitTransition  = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        composable(
            route = Routes.Splash.route,
            enterTransition    = { fadeIn(tween(300)) },
            exitTransition     = { fadeOut(tween(300)) },
            popEnterTransition = { fadeIn(tween(300)) },
            popExitTransition  = { fadeOut(tween(300)) }
        ) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Routes.Onboarding.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            route = Routes.Onboarding.route,
            enterTransition = {
                if (initialState.destination.route == Routes.Splash.route)
                    fadeIn(tween(400))
                else
                    slideInHorizontally(initialOffsetX = { -it / 3 })
            }
        ) {
            OnboardingScreen(
                onAfiliadoClick = { navController.navigate(Routes.Login.route) }
            )
        }

        composable(Routes.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Routes.Main.route) },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Routes.Main.route) {
            MainScreen(
                onSacarTurno = { navController.navigate(Routes.SacarTurno.route) }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen()
        }

        // Flujo 2 — Sacar Turno
        composable(Routes.SacarTurno.route) {
            SacarTurnoScreen(
                onVolverAlMain = { navController.popBackStack(Routes.Main.route, inclusive = false) },
                onConfirmado   = { navController.navigate(Routes.TurnoConfirmado.route) }
            )
        }

        composable(
            route = Routes.TurnoConfirmado.route,
            enterTransition    = { fadeIn(tween(300)) },
            exitTransition     = { fadeOut(tween(200)) },
            popEnterTransition = { fadeIn(tween(300)) },
            popExitTransition  = { fadeOut(tween(200)) }
        ) {
            ConfirmedScreen(
                onVerTurnos    = { navController.popBackStack(Routes.Main.route, inclusive = false) },
                onVolverInicio = { navController.popBackStack(Routes.Main.route, inclusive = false) }
            )
        }
    }
}
