package com.example.saludify.presentation.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*

import androidx.compose.ui.Modifier

import com.example.saludify.presentation.components.BottomBar

import com.example.saludify.presentation.screens.home.HomeScreen
import com.example.saludify.presentation.screens.profile.ProfileScreen
import com.example.saludify.presentation.screens.attention.AttentionScreen
import com.example.saludify.presentation.screens.help.HelpScreen
import com.example.saludify.presentation.screens.procedures.ProceduresScreen
import androidx.compose.foundation.layout.Box

@Composable
fun MainScreen() {

    var selectedScreen by remember {
        mutableStateOf("home")
    }

    Scaffold(

        bottomBar = {

            BottomBar(
                selectedScreen = selectedScreen,
                onScreenSelected = {
                    selectedScreen = it
                }
            )

        }

    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {

            when (selectedScreen) {

                "home" -> HomeScreen()

                "attention" -> AttentionScreen()

                "procedures" -> ProceduresScreen()

                "help" -> HelpScreen()

                "profile" -> ProfileScreen()
            }
        }
    }
    }