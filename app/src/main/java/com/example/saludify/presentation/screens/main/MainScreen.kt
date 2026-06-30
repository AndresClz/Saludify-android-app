package com.example.saludify.presentation.screens.main

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
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
fun MainScreen(onSacarTurno: () -> Unit = {}) {

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
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {

            val tabOrder = listOf("home", "attention", "procedures", "help", "profile")

            AnimatedContent(
                targetState = selectedScreen,
                transitionSpec = {
                    val dir = if (tabOrder.indexOf(targetState) > tabOrder.indexOf(initialState)) 1 else -1
                    slideInHorizontally(tween(280)) { dir * it } + fadeIn(tween(280)) togetherWith
                    slideOutHorizontally(tween(280)) { -dir * it } + fadeOut(tween(200))
                },
                label = "tab"
            ) { screen ->

                when (screen) {

                    "home" -> HomeScreen(onSacarTurno = onSacarTurno)

                    "attention" -> AttentionScreen()

                    "procedures" -> ProceduresScreen()

                    "help" -> HelpScreen()

                    "profile" -> ProfileScreen()
                }
            }
        }
    }
    }