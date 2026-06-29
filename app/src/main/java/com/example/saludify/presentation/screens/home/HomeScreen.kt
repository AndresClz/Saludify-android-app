package com.example.saludify.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Turno
import com.example.saludify.domain.model.Usuario
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BorderMuted
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimaryBorder
import com.example.saludify.ui.theme.BrandPrimaryDark
import com.example.saludify.ui.theme.BrandPrimaryLight
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticDanger
import com.example.saludify.ui.theme.SemanticDangerBorder
import com.example.saludify.ui.theme.SemanticDangerSurface
import com.example.saludify.ui.theme.SemanticDangerSurfaceStrong
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder

private val SectionLabelColor = Color(0xFF8896AA)
@Preview
@Composable
fun HomeScreen(onSacarTurno: () -> Unit = {}) {
    val usuario = MockData.currentUser ?: MockData.usuarios.first()
    val turno = MockData.proximoTurno

    Box(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            HomeHeader(usuario = usuario)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CredencialesSection(usuario = usuario)
                ProximosTurnosSection(turno = turno)
                AccesosRapidosSection(onSacarTurno = onSacarTurno)
                EmergenciasSection()
                Spacer(Modifier.height(16.dp))
            }
        }
        GeminiFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 16.dp)
        )
    }
}

// ── Header ──────────────────────────────────────────────────────────────────

@Composable
private fun HomeHeader(usuario: Usuario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo: badge "S" + wordmark
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(BrandPrimary, SaludifyRadius.icon),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "S",
                    color = TextOnPrimary,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 1.em
                )
            }
            Text(
                text = "Saludify",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = BrandPrimary,
                letterSpacing = (-0.3).sp
            )
        }

        // Right: Ver token + bell + avatar
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // "Ver token" pill
            Row(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(BrandPrimarySurface)
                    .padding(horizontal = 14.dp, vertical = 7.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(SaludifyRadius.full)
                        .background(BrandPrimary)
                )
                Text(
                    text = "Ver token",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary
                )
            }

            // Bell with red dot
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(SaludifyRadius.full)
                    .background(BackgroundSubtle),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = TextMuted,
                    modifier = Modifier.size(18.dp)
                )
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .align(Alignment.TopEnd)
                        .offset(x = (-1).dp, y = 1.dp)
                        .clip(SaludifyRadius.full)
                        .background(BackgroundSurface),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(SaludifyRadius.full)
                            .background(Color(0xFFEF4444))
                    )
                }
            }

            // Avatar
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(SaludifyRadius.full)
                    .background(BrandPrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = usuario.nombre.first().toString(),
                    color = TextOnPrimary,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.em
                )
            }
        }
    }
    HorizontalDivider(color = BorderLight, thickness = 1.dp)
}

// ── Shared: section divider label + line ────────────────────────────────────

@Composable
private fun SectionDivider(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = SectionLabelColor,
            letterSpacing = 0.09.em
        )
        HorizontalDivider(modifier = Modifier.weight(1f), color = BorderDefault, thickness = 1.dp)
    }
}

// ── Credenciales ─────────────────────────────────────────────────────────────

@Composable
private fun CredencialesSection(usuario: Usuario) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Column(modifier = Modifier.padding(top = 20.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TUS CREDENCIALES",
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = SectionLabelColor,
                letterSpacing = 0.09.em
            )
            Text(text = "Ver todas", fontSize = 12.sp, color = BrandPrimary)
        }

        // HorizontalPager: cada página ocupa el ancho disponible menos el peek derecho
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(start = 18.dp, end = 44.dp),
            pageSpacing = 12.dp,
            modifier = Modifier.fillMaxWidth()
        ) { page ->
            when (page) {
                0 -> CredencialCard(
                    nombre = "${usuario.nombre} ${usuario.apellido}",
                    numero = "0000 · 0000 · 1234",
                    labelTitular = "TITULAR",
                    modifier = Modifier.fillMaxWidth()
                )
                else -> CredencialCard(
                    nombre = "Lucas González",
                    numero = "0000 · 0000 · 5678",
                    labelTitular = "AFILIADO",
                    isSecondary = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Dots reactivos al pager
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 11.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = if (pagerState.currentPage == 0) 20.dp else 5.dp, height = 5.dp)
                    .clip(SaludifyRadius.badge)
                    .background(if (pagerState.currentPage == 0) BrandPrimary else BorderMuted)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(width = if (pagerState.currentPage == 1) 20.dp else 5.dp, height = 5.dp)
                    .clip(SaludifyRadius.badge)
                    .background(if (pagerState.currentPage == 1) BrandPrimary else BorderMuted)
            )
        }
    }
}

@Composable
private fun CredencialCard(
    nombre: String,
    numero: String,
    labelTitular: String,
    modifier: Modifier = Modifier,
    isSecondary: Boolean = false
) {
    val gradientColors = if (isSecondary) {
        arrayOf(0f to Color(0xFF163872), 0.52f to Color(0xFF1E4FA8), 1f to Color(0xFF2A62C0))
    } else {
        arrayOf(0f to BrandPrimaryDark, 0.52f to BrandPrimary, 1f to BrandPrimaryLight)
    }

    Box(
        modifier = modifier
            .then(
                if (!isSecondary) Modifier.shadow(
                    elevation = 10.dp,
                    shape = SaludifyRadius.panel,
                    spotColor = BrandPrimary.copy(alpha = 0.28f)
                ) else Modifier
            )
            .clip(SaludifyRadius.panel)
            .background(
                Brush.linearGradient(
                    colorStops = gradientColors,
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            )
    ) {
        // Decorative circles (primary card only)
        if (!isSecondary) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 38.dp, y = (-38).dp)
                    .clip(SaludifyRadius.full)
                    .background(Color.White.copy(alpha = 0.07f))
            )
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 24.dp, y = 20.dp)
                    .clip(SaludifyRadius.full)
                    .background(Color.White.copy(alpha = 0.05f))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 22.dp, vertical = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 22.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Saludify",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnPrimary,
                    letterSpacing = (-0.2).sp
                )
                Box(
                    modifier = Modifier
                        .size(width = 32.dp, height = 22.dp)
                        .clip(SaludifyRadius.buttonSm)
                        .background(Color.White.copy(alpha = 0.2f))
                        .border(1.dp, Color.White.copy(alpha = 0.3f), SaludifyRadius.buttonSm)
                )
            }
            Text(
                text = numero,
                fontSize = 15.sp,
                color = TextOnPrimary,
                letterSpacing = 0.2.em,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = labelTitular,
                        fontSize = 9.sp,
                        color = TextOnPrimary.copy(alpha = 0.6f),
                        letterSpacing = 0.09.em,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Text(
                        text = nombre,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextOnPrimary
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "PLAN",
                        fontSize = 9.sp,
                        color = TextOnPrimary.copy(alpha = 0.6f),
                        letterSpacing = 0.09.em,
                        modifier = Modifier.padding(bottom = 3.dp)
                    )
                    Text(
                        text = "Premium",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextOnPrimary
                    )
                }
            }
        }
    }
}

// ── Próximo turno ─────────────────────────────────────────────────────────────

@Composable
private fun ProximosTurnosSection(turno: Turno) {
    Column(modifier = Modifier.padding(top = 18.dp)) {
        SectionDivider(title = "PRÓXIMO TURNO")
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .shadow(
                    elevation = 2.dp,
                    shape = SaludifyRadius.cardLg,
                    spotColor = BrandPrimary.copy(alpha = 0.10f)
                )
                .clip(SaludifyRadius.cardLg)
                .background(BackgroundSurface)
                .border(1.5.dp, BrandPrimaryBorder, SaludifyRadius.cardLg)
        ) {
            // Blue header bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrandPrimary)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(SaludifyRadius.full)
                            .background(Color(0xFF4ADE80))
                    )
                    Text(
                        text = "${turno.diaSemana} ${turno.dia} ${turno.mes} · ${turno.hora}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextOnPrimary
                    )
                }
                Text(
                    text = "en 2 días",
                    fontSize = 11.sp,
                    color = TextOnPrimary.copy(alpha = 0.65f)
                )
            }
            // Body
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(SaludifyRadius.full)
                        .background(BrandPrimarySurface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "RS",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = BrandPrimary,
                        lineHeight = 1.em
                    )
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = turno.medico,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextDefault,
                        letterSpacing = (-0.2).sp,
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                    Text(
                        text = "${turno.especialidad} · ${turno.lugar}",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
                Box(
                    modifier = Modifier
                        .clip(SaludifyRadius.badge)
                        .background(BrandPrimarySurface)
                        .padding(horizontal = 12.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = "Detalle",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandPrimary
                    )
                }
            }
        }
    }
}

// ── Accesos rápidos ───────────────────────────────────────────────────────────

@Composable
private fun AccesosRapidosSection(onSacarTurno: () -> Unit) {
    Column(modifier = Modifier.padding(top = 18.dp)) {
        SectionDivider(title = "ACCESOS RÁPIDOS")
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickAccessCard(label = "Sacar turno", icon = Icons.Filled.Add, onClick = onSacarTurno, modifier = Modifier.weight(1f))
                QuickAccessCard(label = "Mis turnos", icon = Icons.Filled.CalendarToday, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                QuickAccessCard(label = "Reintegros", icon = Icons.Filled.ArrowDownward, modifier = Modifier.weight(1f))
                QuickAccessCard(label = "Cartilla", icon = Icons.Filled.Apps, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun QuickAccessCard(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .shadow(elevation = 1.dp, shape = SaludifyRadius.card)
            .clip(SaludifyRadius.card)
            .clickable(onClick = onClick)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.card)
            .padding(horizontal = 14.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(SaludifyRadius.chip)
                .background(BrandPrimarySurface),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = BrandPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDefault,
            lineHeight = 1.3.em
        )
    }
}

// ── Emergencias ───────────────────────────────────────────────────────────────

@Composable
private fun EmergenciasSection() {
    Column(modifier = Modifier.padding(top = 14.dp)) {
        SectionDivider(title = "EMERGENCIAS")
        Spacer(Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .clip(SaludifyRadius.iconLg)
                .background(SemanticDangerSurface)
                .border(1.dp, SemanticDangerBorder, SaludifyRadius.iconLg)
                .padding(horizontal = 14.dp, vertical = 11.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(SaludifyRadius.iconMd)
                    .background(SemanticDangerSurfaceStrong),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Phone,
                    contentDescription = null,
                    tint = SemanticDanger,
                    modifier = Modifier.size(16.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Línea de emergencias",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    modifier = Modifier.padding(bottom = 1.dp)
                )
                Text(
                    text = "0800 333 4444",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = SemanticDanger,
                    letterSpacing = (-0.1).sp
                )
            }
            Box(
                modifier = Modifier
                    .clip(SaludifyRadius.badge)
                    .background(SemanticDanger)
                    .padding(horizontal = 14.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Llamar",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextOnPrimary
                )
            }
        }
    }
}

// ── FAB Gemini ────────────────────────────────────────────────────────────────

@Composable
private fun GeminiFab(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(52.dp)
            .shadow(
                elevation = 8.dp,
                shape = SaludifyRadius.full,
                spotColor = Color(0xFF7C3AED).copy(alpha = 0.45f)
            )
            .clip(SaludifyRadius.full)
            .background(
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF4285F4),
                        0.55f to Color(0xFF7C3AED),
                        1f to Color(0xFFDB2777)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.AutoAwesome,
            contentDescription = "Chat IA",
            tint = TextOnPrimary,
            modifier = Modifier.size(22.dp)
        )
    }
}
