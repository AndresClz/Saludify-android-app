package com.example.saludify.presentation.screens.confirmed

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Medico
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.SemanticSuccessSurfaceStrong
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

@Composable
fun ConfirmedScreen(
    onVerTurnos: () -> Unit,
    onVolverInicio: () -> Unit
) {
    val medico = MockData.medicos.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
            .statusBarsPadding()
            .padding(horizontal = 18.dp)
            .verticalScroll(rememberScrollState())
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(48.dp))

        SuccessIcon()

        Spacer(Modifier.height(22.dp))

        Text(
            text = "¡Turno confirmado!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = TextDefault,
            letterSpacing = (-0.5).sp,
            lineHeight = 1.2.em,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(6.dp))

        Text(
            text = "Te enviamos la confirmación\npor notificación y email",
            fontSize = 14.sp,
            color = TextMuted,
            lineHeight = 1.6.em,
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(32.dp))

        ResumenCard(medico = medico)

        Spacer(Modifier.height(28.dp))

        CalendarCard()

        Spacer(Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(SaludifyRadius.button)
                .background(BrandPrimary)
                .clickable(onClick = onVerTurnos)
                .padding(vertical = 17.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Ver mis turnos",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextOnPrimary
            )
        }

        Spacer(Modifier.height(10.dp))

        Text(
            text = "Volver al inicio",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextMuted,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .clickable(onClick = onVolverInicio)
                .padding(vertical = 4.dp)
        )

        Spacer(Modifier.height(24.dp))
    }
}

// ── Success icon ───────────────────────────────────────────────────────────────

@Composable
private fun SuccessIcon() {
    // Outer ring: SemanticSuccessSurface (#f0fdf4), 12dp de padding visual
    Box(
        modifier = Modifier
            .size(112.dp)   // 88 + 12*2
            .clip(SaludifyRadius.full)
            .background(SemanticSuccessSurface),
        contentAlignment = Alignment.Center
    ) {
        // Inner circle: SemanticSuccessSurfaceStrong (#dcfce7)
        Box(
            modifier = Modifier
                .size(88.dp)
                .clip(SaludifyRadius.full)
                .background(SemanticSuccessSurfaceStrong),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = null,
                tint = SemanticSuccess,
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

// ── Resumen card ───────────────────────────────────────────────────────────────

@Composable
private fun ResumenCard(medico: Medico) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = SaludifyRadius.panel,
                spotColor = BrandPrimary.copy(alpha = 0.08f)
            )
            .clip(SaludifyRadius.panel)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.panel)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Avatar + doctor
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(SaludifyRadius.full)
                    .background(BrandPrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "RS",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnPrimary,
                    lineHeight = 1.em
                )
            }
            Column {
                Text(
                    text = medico.nombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDefault
                )
                Text(text = medico.especialidad, fontSize = 12.sp, color = TextMuted)
            }
        }

        HorizontalDivider(color = BorderDefault, thickness = 1.dp)

        // Fecha + Hora en dos columnas
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                ResumenLabel(text = "FECHA")
                Text(
                    text = "${medico.diaSemana} ${medico.dia} ${medico.mes}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                ResumenLabel(text = "HORA")
                Text(
                    text = medico.hora,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
            }
        }

        // Lugar
        Column {
            ResumenLabel(text = "LUGAR")
            val lugarCompleto = if (medico.direccion.isNotEmpty())
                "${medico.lugar} · ${medico.direccion}"
            else
                medico.lugar
            Text(
                text = lugarCompleto,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDefault
            )
        }
    }
}

@Composable
private fun ResumenLabel(text: String) {
    Text(
        text = text,
        fontSize = 10.sp,
        color = TextPlaceholder,
        letterSpacing = 0.07.em,
        modifier = Modifier.padding(bottom = 3.dp)
    )
}

// ── Calendar card (decorativa) ─────────────────────────────────────────────────

@Composable
private fun CalendarCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = SaludifyRadius.button,
                spotColor = Color.Black.copy(alpha = 0.04f)
            )
            .clip(SaludifyRadius.button)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.button)
            .padding(14.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Outlined.CalendarToday,
            contentDescription = null,
            tint = TextSecondary,
            modifier = Modifier.size(18.dp)
        )
        Spacer(Modifier.size(10.dp))
        Text(
            text = "Agregar al calendario",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary
        )
    }
}
