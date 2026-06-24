package com.example.saludify.presentation.components

import androidx.compose.material3.*

import androidx.compose.runtime.Composable

@Composable
fun BottomBar(
    selectedScreen: String,
    onScreenSelected: (String) -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = selectedScreen == "home",
            onClick = {
                onScreenSelected("home")
            },
            icon = {
                Text("🏠")
            },
            label = {
                Text("Home")
            }
        )

        NavigationBarItem(
            selected = selectedScreen == "attention",
            onClick = {
                onScreenSelected("attention")
            },
            icon = {
                Text("🏥")
            },
            label = {
                Text("Atención")
            }
        )

        NavigationBarItem(
            selected = selectedScreen == "procedures",
            onClick = {
                onScreenSelected("procedures")
            },
            icon = {
                Text("📋")
            },
            label = {
                Text("Trámites")
            }
        )

        NavigationBarItem(
            selected = selectedScreen == "help",
            onClick = {
                onScreenSelected("help")
            },
            icon = {
                Text("❓")
            },
            label = {
                Text("Ayuda")
            }
        )

        NavigationBarItem(
            selected = selectedScreen == "profile",
            onClick = {
                onScreenSelected("profile")
            },
            icon = {
                Text("👤")
            },
            label = {
                Text("Perfil")
            }
        )
    }
}