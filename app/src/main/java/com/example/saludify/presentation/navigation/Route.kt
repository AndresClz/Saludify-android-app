package com.example.saludify.presentation.navigation

sealed class Routes(val route: String) {

    object Login : Routes("login")
    object Main : Routes("main")

    object Home : Routes("home")
    object Attention : Routes("attention")
    object Procedures : Routes("procedures")
    object Help : Routes("help")
    object Profile : Routes("profile")
}