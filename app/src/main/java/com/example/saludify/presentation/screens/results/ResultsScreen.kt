package com.example.saludify.presentation.screens.results

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.LocationOn
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
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Medico
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSegmented
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.SemanticSuccessSurfaceStrong
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val StepperEmpty = Color(0xFFE5E7EB)

@Composable
fun ResultsScreen(
    onBackClick: () -> Unit,
    onReservar: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        ResultsHeader(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            // Segmented control
            Box(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 12.dp)) {
                SegmentedControl()
            }

            // Controls + cards
            Column(
                modifier = Modifier.padding(
                    start = 18.dp,
                    end = 18.dp,
                    top = 10.dp,
                    bottom = 14.dp
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Ordenar", fontSize = 12.sp, color = BrandPrimary)
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "12 disponibles",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary
                    )
                }

                MockData.medicos.forEach { medico ->
                    MedicoCard(medico = medico, onReservar = onReservar)
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

// ── Header ─────────────────────────────────────────────────────────────────────

@Composable
private fun ResultsHeader(onBackClick: () -> Unit) {
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
                text = "Resultados",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )

            // Ícono filtro — 3 líneas de ancho decreciente
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
        }

        // Stepper: 3/4 llenos
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
                        .background(if (index < 3) BrandPrimary else StepperEmpty)
                )
            }
        }

        HorizontalDivider(color = BorderLight, thickness = 1.dp)
    }
}

// ── Segmented control ──────────────────────────────────────────────────────────

@Composable
private fun SegmentedControl() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(SaludifyRadius.segmented)
            .background(BackgroundSegmented)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // "Turnos" activo
        Row(
            modifier = Modifier
                .weight(1f)
                .shadow(
                    elevation = 1.dp,
                    shape = SaludifyRadius.segmentedItem,
                    spotColor = Color.Black.copy(alpha = 0.10f)
                )
                .clip(SaludifyRadius.segmentedItem)
                .background(BackgroundSurface)
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Turnos",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDefault
            )
            Spacer(Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(BrandPrimary)
                    .padding(horizontal = 7.dp, vertical = 1.dp)
            ) {
                Text(
                    text = "12",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnPrimary
                )
            }
        }

        // "Cartilla" inactivo
        Box(
            modifier = Modifier
                .weight(1f)
                .clip(SaludifyRadius.segmentedItem)
                .padding(vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Cartilla", fontSize = 13.sp, color = TextMuted)
        }
    }
}

// ── MedicoCard ─────────────────────────────────────────────────────────────────

@Composable
private fun MedicoCard(medico: Medico, onReservar: () -> Unit) {
    val isOnline  = medico.modalidad == "Online"
    val badgeBg   = if (isOnline) SemanticSuccessSurfaceStrong else BrandPrimarySurface
    val badgeText = if (isOnline) SemanticSuccess else BrandPrimary
    val slotBg    = if (isOnline) SemanticSuccessSurface else BackgroundSubtle

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 1.dp,
                shape = SaludifyRadius.cardLg,
                spotColor = Color.Black.copy(alpha = 0.05f)
            )
            .clip(SaludifyRadius.cardLg)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 15.dp, end = 16.dp)) {

            // Nombre + badge modalidad
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                    Text(
                        text = medico.nombre,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDefault,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(text = medico.especialidad, fontSize = 12.sp, color = TextMuted)
                }
                Box(
                    modifier = Modifier
                        .clip(SaludifyRadius.badge)
                        .background(badgeBg)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = medico.modalidad,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = badgeText
                    )
                }
            }

            // Ubicación
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (isOnline) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(SaludifyRadius.full)
                            .background(SemanticSuccess)
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = null,
                        tint = TextPlaceholder,
                        modifier = Modifier.size(13.dp)
                    )
                }
                val locationText = if (medico.direccion.isNotEmpty())
                    "${medico.lugar} · ${medico.direccion}"
                else
                    medico.lugar
                Text(text = locationText, fontSize = 12.sp, color = TextMuted)
            }

            Spacer(Modifier.height(12.dp))
        }

        // Footer con slot + botón Reservar
        HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .clip(SaludifyRadius.chip)
                    .background(slotBg)
                    .padding(horizontal = 12.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Próximo turno",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    modifier = Modifier.padding(bottom = 1.dp)
                )
                Text(
                    text = "${medico.diaSemana} ${medico.dia} ${medico.mes} · ${medico.hora}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDefault
                )
            }

            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.buttonSm)
                    .background(BrandPrimary)
                    .clickable(onClick = onReservar)
                    .padding(horizontal = 18.dp, vertical = 10.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Reservar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextOnPrimary
                )
            }
        }
    }
}
