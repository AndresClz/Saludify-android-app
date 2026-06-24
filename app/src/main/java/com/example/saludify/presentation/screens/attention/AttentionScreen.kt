package com.example.saludify.presentation.screens.attention

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.saludify.data.MockData

@Composable
fun AttentionScreen() {
    LazyColumn {
        items(MockData.especialidades) { especialidad ->
            Card {
                Text(text = especialidad.nombre)
            }
        }
    }
}
