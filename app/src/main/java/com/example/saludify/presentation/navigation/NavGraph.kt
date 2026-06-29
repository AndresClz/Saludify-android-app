package com.example.saludify.presentation.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.saludify.presentation.screens.forwhom.ForWhomScreen
import com.example.saludify.presentation.screens.login.LoginScreen
import com.example.saludify.presentation.screens.main.MainScreen
import com.example.saludify.presentation.screens.onboarding.OnboardingScreen
import com.example.saludify.presentation.screens.profile.ProfileScreen
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

        // Stubs temporales — se implementan en la próxima iteración
        composable(Routes.Resultados.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Resultados — próximamente")
            }
        }

        composable(Routes.ConfirmarTurno.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Confirmar turno — próximamente")
            }
        }

        composable(Routes.TurnoConfirmado.route) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Turno confirmado — próximamente")
            }
        }
    }
}
