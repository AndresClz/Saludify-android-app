package com.example.saludify.presentation.screens.sacaturno

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.presentation.screens.confirm.ConfirmScreen
import com.example.saludify.presentation.screens.forwhom.ForWhomScreen
import com.example.saludify.presentation.screens.results.ResultsScreen
import com.example.saludify.presentation.screens.search.SearchScreen
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextSecondary

private val StepperEmpty = Color(0xFFE5E7EB)

@Composable
fun SacarTurnoScreen(
    onVolverAlMain: () -> Unit,
    onConfirmado: () -> Unit
) {
    val usuario = MockData.currentUser ?: MockData.usuarios.first()
    var step by remember { mutableIntStateOf(0) }

    BackHandler(enabled = step in 1..3) { step-- }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
            .navigationBarsPadding()
    ) {
        SacarTurnoHeader(
            step = step,
            nombreUsuario = "${usuario.nombre} ${usuario.apellido}",
            onBackClick = { if (step > 0) step-- else onVolverAlMain() }
        )

        AnimatedContent(
            targetState = step,
            transitionSpec = {
                val dir = if (targetState > initialState) 1 else -1
                slideInHorizontally(tween(280)) { dir * it } + fadeIn(tween(280)) togetherWith
                slideOutHorizontally(tween(280)) { -dir * it } + fadeOut(tween(200))
            },
            label = "sacar_turno_step"
        ) { currentStep ->
            when (currentStep) {
                0 -> ForWhomScreen(onContinuar = { step++ })
                1 -> SearchScreen(onNext = { step++ })
                2 -> ResultsScreen(onReservar = { step++ })
                3 -> ConfirmScreen(onConfirmar = onConfirmado)
            }
        }
    }
}

@Composable
private fun SacarTurnoHeader(
    step: Int,
    nombreUsuario: String,
    onBackClick: () -> Unit
) {
    val title = when (step) {
        0    -> "Sacar turno"
        1    -> "Sacar turno · $nombreUsuario"
        2    -> "Resultados"
        else -> "Confirmar turno"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(SaludifyRadius.full)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = TextDefault,
                    modifier = Modifier.size(20.dp)
                )
            }

            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            // Ícono filtro solo en Resultados; placeholder en el resto
            if (step == 2) {
                Column(
                    modifier = Modifier.size(28.dp),
                    verticalArrangement = Arrangement.spacedBy(2.5.dp, Alignment.CenterVertically),
                    horizontalAlignment = Alignment.End
                ) {
                    listOf(16.dp, 11.dp, 7.dp).forEach { lineWidth ->
                        Box(
                            modifier = Modifier
                                .width(lineWidth)
                                .height(2.dp)
                                .clip(SaludifyRadius.badge)
                                .background(TextSecondary)
                        )
                    }
                }
            } else {
                Spacer(Modifier.size(28.dp))
            }
        }

        // Stepper con color animado entre pasos
        val filledCount = step + 1
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) { index ->
                val color by animateColorAsState(
                    targetValue = if (index < filledCount) BrandPrimary else StepperEmpty,
                    animationSpec = tween(300),
                    label = "stepper_$index"
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(3.dp)
                        .clip(SaludifyRadius.badge)
                        .background(color)
                )
            }
        }

        HorizontalDivider(color = BorderLight, thickness = 1.dp)
    }
}
