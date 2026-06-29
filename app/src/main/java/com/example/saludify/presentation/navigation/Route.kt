package com.example.saludify.presentation.navigation

sealed class Routes(val route: String) {

    object Onboarding : Routes("onboarding")
    object Login : Routes("login")
    object Main : Routes("main")

    object Home : Routes("home")
    object Attention : Routes("attention")
    object Procedures : Routes("procedures")
    object Help : Routes("help")
    object Profile : Routes("profile")

    // Flujo 2 — Sacar Turno
    object ParaQuien       : Routes("para_quien")
    object Buscar          : Routes("buscar")
    object Resultados      : Routes("resultados")
    object ConfirmarTurno  : Routes("confirmar_turno")
    object TurnoConfirmado : Routes("turno_confirmado")
}