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
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
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
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Medico
import com.example.saludify.domain.model.MedicoCartilla
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSegmented
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BorderMuted
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimaryBorder
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.SemanticSuccessSurfaceStrong
import com.example.saludify.ui.theme.SemanticPurple
import com.example.saludify.ui.theme.SemanticPurpleSurface
import com.example.saludify.ui.theme.SemanticWarningBorder
import com.example.saludify.ui.theme.SemanticWarningSurface
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val StepperEmpty          = Color(0xFFE5E7EB)
private val BadgeGrayBg           = Color(0xFFD1D5DB)
private val WarningTextDark       = Color(0xFF92400E)
private val CartillaBorderSuccess = Color(0xFFBBF7D0)

@Composable
fun ResultsScreen(
    onBackClick: () -> Unit,
    onReservar: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        ResultsHeader(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 12.dp)) {
                SegmentedControl(
                    selectedTab = selectedTab,
                    onTabSelect = { selectedTab = it }
                )
            }

            if (selectedTab == 0) {
                TurnosContent(onReservar = onReservar)
            } else {
                CartillaContent()
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
private fun SegmentedControl(selectedTab: Int, onTabSelect: (Int) -> Unit) {
    val turnosActive   = selectedTab == 0
    val cartillaActive = selectedTab == 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(SaludifyRadius.segmented)
            .background(BackgroundSegmented)
            .padding(3.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        // Tab "Turnos"
        Row(
            modifier = Modifier
                .weight(1f)
                .then(
                    if (turnosActive)
                        Modifier.shadow(1.dp, SaludifyRadius.segmentedItem, spotColor = Color.Black.copy(0.1f))
                    else Modifier
                )
                .clip(SaludifyRadius.segmentedItem)
                .background(if (turnosActive) BackgroundSurface else Color.Transparent)
                .clickable { onTabSelect(0) }
                .padding(vertical = 9.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Turnos",
                fontSize = 13.sp,
                fontWeight = if (turnosActive) FontWeight.SemiBold else FontWeight.Normal,
                color = if (turnosActive) TextDefault else TextMuted
            )
            Spacer(Modifier.width(6.dp))
            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(if (turnosActive) BrandPrimary else BadgeGrayBg)
                    .padding(horizontal = 7.dp, vertical = 1.dp)
            ) {
                Text(
                    text = "12",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (turnosActive) TextOnPrimary else TextMuted
                )
            }
        }

        // Tab "Cartilla"
        Box(
            modifier = Modifier
                .weight(1f)
                .then(
                    if (cartillaActive)
                        Modifier.shadow(1.dp, SaludifyRadius.segmentedItem, spotColor = Color.Black.copy(0.1f))
                    else Modifier
                )
                .clip(SaludifyRadius.segmentedItem)
                .background(if (cartillaActive) BackgroundSurface else Color.Transparent)
                .clickable { onTabSelect(1) }
                .padding(vertical = 9.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cartilla",
                fontSize = 13.sp,
                fontWeight = if (cartillaActive) FontWeight.SemiBold else FontWeight.Normal,
                color = if (cartillaActive) TextDefault else TextMuted
            )
        }
    }
}

// ── Turnos content ─────────────────────────────────────────────────────────────

@Composable
private fun TurnosContent(onReservar: () -> Unit) {
    Column(
        modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 10.dp, bottom = 14.dp),
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
            MedicoTurnoCard(medico = medico, onReservar = onReservar)
        }
    }
}

@Composable
private fun MedicoTurnoCard(medico: Medico, onReservar: () -> Unit) {
    val isOnline  = medico.modalidad == "Online"
    val badgeBg   = if (isOnline) SemanticSuccessSurfaceStrong else BrandPrimarySurface
    val badgeText = if (isOnline) SemanticSuccess else BrandPrimary
    val slotBg    = if (isOnline) SemanticSuccessSurface else BackgroundSubtle

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, SaludifyRadius.cardLg, spotColor = Color.Black.copy(0.05f))
            .clip(SaludifyRadius.cardLg)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
    ) {
        Column(modifier = Modifier.padding(start = 16.dp, top = 15.dp, end = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
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
                else medico.lugar
                Text(text = locationText, fontSize = 12.sp, color = TextMuted)
            }

            Spacer(Modifier.height(12.dp))
        }

        HorizontalDivider(color = Color(0xFFF3F4F6), thickness = 1.dp)
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
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

// ── Cartilla content ───────────────────────────────────────────────────────────

@Composable
private fun CartillaContent() {
    Column(
        modifier = Modifier.padding(horizontal = 18.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Info banner
        Spacer(Modifier.height(2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(SaludifyRadius.card)
                .background(SemanticWarningSurface)
                .border(1.dp, SemanticWarningBorder, SaludifyRadius.card)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(9.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 1.dp)
                    .size(16.dp)
                    .clip(SaludifyRadius.full)
                    .background(Color(0xFFF59E0B)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "!",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    lineHeight = 1.em
                )
            }
            Text(
                text = "Los médicos de cartilla no tienen agenda online. Contactalos directamente para sacar turno.",
                fontSize = 12.sp,
                color = WarningTextDark,
                lineHeight = 1.5.em,
                modifier = Modifier.weight(1f)
            )
        }

        // Contador
        Text(
            text = "24 médicos en CABA",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )

        // Cards
        MockData.medicosCartilla.forEach { medico ->
            MedicoCartillaCard(medico = medico)
        }
    }
}

@Composable
private fun MedicoCartillaCard(medico: MedicoCartilla) {
    val avatarBg   = if (medico.avatarToken == "purple") SemanticPurpleSurface else BrandPrimarySurface
    val avatarText = if (medico.avatarToken == "purple") SemanticPurple else BrandPrimary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, SaludifyRadius.cardLg, spotColor = Color.Black.copy(0.04f))
            .clip(SaludifyRadius.cardLg)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
            .padding(15.dp, 15.dp, 16.dp, 15.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Avatar + info
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.Top
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(SaludifyRadius.full)
                    .background(avatarBg),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = medico.iniciales,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = avatarText,
                    lineHeight = 1.em
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medico.nombre,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDefault,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = medico.especialidad,
                    fontSize = 12.sp,
                    color = TextMuted,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Text(
                    text = "${medico.lugar}\n${medico.direccion}",
                    fontSize = 12.sp,
                    color = TextMuted,
                    lineHeight = 1.5.em
                )
            }
        }

        // Chips horario + estado
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Horario
            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(BackgroundSubtle)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(text = medico.horario, fontSize = 11.sp, color = TextSecondary)
            }
            // Estado
            val estadoBg   = if (medico.abierto) SemanticSuccessSurfaceStrong else BackgroundSubtle
            val estadoText = if (medico.abierto) SemanticSuccess else TextPlaceholder
            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(estadoBg)
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = medico.statusLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = estadoText
                )
            }
        }

        // Botones Llamar / WhatsApp
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            // Llamar
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(SaludifyRadius.buttonSm)
                    .background(BrandPrimarySurface)
                    .border(1.5.dp, BrandPrimaryBorder, SaludifyRadius.buttonSm)
                    .padding(vertical = 11.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = null,
                    tint = BrandPrimary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "Llamar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary
                )
            }
            // WhatsApp
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(SaludifyRadius.buttonSm)
                    .background(SemanticSuccessSurface)
                    .border(1.5.dp, CartillaBorderSuccess, SaludifyRadius.buttonSm)
                    .padding(vertical = 11.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Chat,
                    contentDescription = null,
                    tint = SemanticSuccess,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    text = "WhatsApp",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = SemanticSuccess
                )
            }
        }
    }
}
