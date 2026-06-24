package com.example.saludify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.saludify.presentation.navigation.NavGraph
import com.example.saludify.ui.theme.SaludifyTheme















class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SaludifyTheme {

                NavGraph()

            }
        }
    }
}




