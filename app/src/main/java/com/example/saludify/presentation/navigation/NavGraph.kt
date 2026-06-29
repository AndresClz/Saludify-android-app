package com.example.saludify.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.saludify.presentation.screens.confirm.ConfirmScreen
import com.example.saludify.presentation.screens.confirmed.ConfirmedScreen
import com.example.saludify.presentation.screens.forwhom.ForWhomScreen
import com.example.saludify.presentation.screens.login.LoginScreen
import com.example.saludify.presentation.screens.main.MainScreen
import com.example.saludify.presentation.screens.onboarding.OnboardingScreen
import com.example.saludify.presentation.screens.profile.ProfileScreen
import com.example.saludify.presentation.screens.results.ResultsScreen
import com.example.saludify.presentation.screens.search.SearchScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Onboarding.route,
        enterTransition    = { slideInHorizontally(initialOffsetX = { it }) },
        exitTransition     = { slideOutHorizontally(targetOffsetX = { -it / 3 }) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -it / 3 }) },
        popExitTransition  = { slideOutHorizontally(targetOffsetX = { it }) }
    ) {
        composable(Routes.Onboarding.route) {
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
                onSacarTurno = { navController.navigate(Routes.ParaQuien.route) }
            )
        }

        composable(Routes.Profile.route) {
            ProfileScreen()
        }

        // Flujo 2 — Sacar Turno
        composable(Routes.ParaQuien.route) {
            ForWhomScreen(
                onBackClick  = { navController.popBackStack() },
                onContinuar  = { navController.navigate(Routes.Buscar.route) }
            )
        }

        composable(Routes.Buscar.route) {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onNext      = { navController.navigate(Routes.Resultados.route) }
            )
        }

        composable(Routes.Resultados.route) {
            ResultsScreen(
                onBackClick = { navController.popBackStack() },
                onReservar  = { navController.navigate(Routes.ConfirmarTurno.route) }
            )
        }

        composable(Routes.ConfirmarTurno.route) {
            ConfirmScreen(
                onBackClick = { navController.popBackStack() },
                onConfirmar = { navController.navigate(Routes.TurnoConfirmado.route) }
            )
        }

        composable(Routes.TurnoConfirmado.route) {
            ConfirmedScreen(
                onVerTurnos    = { navController.popBackStack(Routes.Main.route, inclusive = false) },
                onVolverInicio = { navController.popBackStack(Routes.Main.route, inclusive = false) }
            )
        }
    }
}
