package com.example.saludify.presentation.screens.forwhom

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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BorderMuted
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val StepperEmpty = Color(0xFFE5E7EB)

private data class Paciente(
    val nombre: String,
    val inicial: String,
    val subtitulo: String,
    val avatarBg: Color,
    val avatarTextColor: Color
)

private val pacientes = listOf(
    Paciente("María González",  "M", "Titular · DNI 12.345.678",       BrandPrimary,       Color.White),
    Paciente("Lucas González",  "L", "Hijo · 8 años · DNI 47.123.456", SemanticSuccessSurface, SemanticSuccess),
    Paciente("Roberto González","R", "Cónyuge · DNI 30.987.654",       BrandPrimarySurface,    BrandPrimary)
)

@Composable
fun ForWhomScreen(
    onBackClick: () -> Unit,
    onContinuar: () -> Unit
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {
        ForWhomHeader(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
        ) {
            // Título + subtítulo
            Text(
                text = "¿Para quién es el turno?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.4).sp,
                lineHeight = 1.2.em,
                modifier = Modifier.padding(top = 24.dp, bottom = 6.dp)
            )
            Text(
                text = "Seleccioná el paciente",
                fontSize = 13.sp,
                color = TextPlaceholder,
                modifier = Modifier.padding(bottom = 28.dp)
            )

            // Cards de paciente
            pacientes.forEachIndexed { index, paciente ->
                PacienteCard(
                    paciente = paciente,
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index }
                )
                Spacer(Modifier.height(10.dp))
            }

            // Card "Agregar integrante"
            AgregarIntegranteCard()

            Spacer(Modifier.height(24.dp))
        }

        // CTA anclado al fondo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundApp)
                .padding(horizontal = 18.dp, vertical = 28.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(SaludifyRadius.button)
                    .background(BrandPrimary)
                    .clickable(onClick = onContinuar)
                    .padding(vertical = 17.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextOnPrimary
                )
            }
        }
    }
}

// ── Header sticky ─────────────────────────────────────────────────────────────

@Composable
private fun ForWhomHeader(onBackClick: () -> Unit) {
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
                text = "Sacar turno",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(28.dp))
        }

        // Stepper: 1/4 lleno
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(3.dp)
                        .clip(SaludifyRadius.badge)
                        .background(if (index < 1) BrandPrimary else StepperEmpty)
                )
            }
        }

        HorizontalDivider(color = BorderLight, thickness = 1.dp)
    }
}

// ── Cards ─────────────────────────────────────────────────────────────────────

@Composable
private fun PacienteCard(
    paciente: Paciente,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = if (selected) 4.dp else 1.dp,
                shape = SaludifyRadius.cardXl,
                spotColor = if (selected) BrandPrimary.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.04f)
            )
            .clip(SaludifyRadius.cardXl)
            .background(BackgroundSurface)
            .border(
                width = if (selected) 2.dp else 1.5.dp,
                color = if (selected) BrandPrimary else BorderDefault,
                shape = SaludifyRadius.cardXl
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(SaludifyRadius.full)
                .background(paciente.avatarBg),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = paciente.inicial,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = paciente.avatarTextColor,
                lineHeight = 1.em
            )
        }

        // Texto
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = paciente.nombre,
                fontSize = 14.sp,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
                color = TextDefault,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = paciente.subtitulo,
                fontSize = 12.sp,
                color = if (selected) TextMuted else TextPlaceholder
            )
        }

        // Indicador selección
        if (selected) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(SaludifyRadius.full)
                    .background(BrandPrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = TextOnPrimary,
                    modifier = Modifier.size(12.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(SaludifyRadius.full)
                    .border(2.dp, BorderDefault, SaludifyRadius.full)
            )
        }
    }
}

@Composable
private fun AgregarIntegranteCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.5.dp, BorderMuted, SaludifyRadius.cardXl)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(42.dp)
                .clip(SaludifyRadius.full)
                .background(BackgroundSubtle),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
                tint = TextPlaceholder,
                modifier = Modifier.size(16.dp)
            )
        }
        Text(
            text = "Agregar integrante",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = TextPlaceholder
        )
    }
}
