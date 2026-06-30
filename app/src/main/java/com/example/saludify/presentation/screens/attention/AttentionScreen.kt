package com.example.saludify.presentation.screens.attention

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.*

@Preview
@Composable
fun AttentionScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            AttentionHeader()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 16.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { AttentionHeroCard() }
                item { Spacer(Modifier.height(8.dp)) }

                item {
                    AttentionMenuCard(
                        icon = Icons.Outlined.Search,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Cartilla médica",
                        subtitle = "Buscar médico o especialidad"
                    )
                }
                item {
                    AttentionMenuCard(
                        icon = Icons.Outlined.VerifiedUser,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Mi cobertura",
                        subtitle = "Plan y prestaciones incluidas",
                        badge = "Premium",
                        badgeBackground = BrandPrimarySurface,
                        badgeTextColor = BrandPrimary
                    )
                }
                item {
                    AttentionMenuCard(
                        icon = Icons.Outlined.AddCircle,
                        iconBackground = SemanticSuccessSurface,
                        iconTint = SemanticSuccess,
                        title = "Sacar turno",
                        subtitle = "Reservar una nueva consulta"
                    )
                }
                item {
                    AttentionMenuCard(
                        icon = Icons.Outlined.CalendarMonth,
                        iconBackground = SemanticInfoSurface,
                        iconTint = SemanticInfo,
                        title = "Mis turnos",
                        subtitle = "Próximos · cancelar",
                        badge = "1",
                        badgeBackground = SemanticWarningSurfaceStrong,
                        badgeTextColor = SemanticWarning
                    )
                }
                item {
                    AttentionMenuCard(
                        icon = Icons.Outlined.Description,
                        iconBackground = SemanticPurpleSurface,
                        iconTint = SemanticPurple,
                        title = "Historial de consultas",
                        subtitle = "Mis atenciones anteriores"
                    )
                }
            }
        }

        GeminiFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 74.dp)
        )
    }
}

@Composable
private fun AttentionHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
            .statusBarsPadding()
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                text = "Atención",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.3).sp
            )
        }
        Box(modifier = Modifier.size(24.dp))
    }
    HorizontalDivider(color = BorderLight)
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewAttention() {
    SaludifyTheme {
        Surface {
            AttentionScreen()
        }
    }
}

@Composable
private fun AttentionHeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(elevation = 8.dp, shape = SaludifyRadius.panel)
            .clip(SaludifyRadius.panel)
            .background(
                Brush.linearGradient(
                    colors = listOf(BrandPrimaryDark, BrandPrimary, BrandPrimaryLight)
                )
            )
    ) {
        Box(
            modifier = Modifier
                .size(130.dp)
                .align(Alignment.TopEnd)
                .offset(x = 35.dp, y = (-35).dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.08f))
        )
        Box(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 25.dp, y = 25.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.05f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Buenos días, María",
                color = TextOnPrimary.copy(alpha = 0.65f),
                fontSize = 12.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "¿Qué necesitás hoy?",
                color = TextOnPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.3).sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                HeroChip(text = "Turno online", dotColor = Color(0xFF4ADE80))
                HeroChip(text = "Urgencias", dotColor = Color(0xFFF87171))
            }
        }
    }
}

@Composable
private fun HeroChip(text: String, dotColor: Color) {
    Row(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(Color.White.copy(alpha = 0.18f))
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(dotColor)
        )
        Text(
            text = text,
            color = TextOnPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun AttentionMenuCard(
    icon: ImageVector,
    iconBackground: Color,
    iconTint: Color,
    title: String,
    subtitle: String,
    badge: String? = null,
    badgeBackground: Color = BrandPrimarySurface,
    badgeTextColor: Color = BrandPrimary,
    onClick: () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue = if (pressed) BrandPrimarySurface else BackgroundSurface,
        animationSpec = tween(180),
        label = ""
    )
    val borderColor by animateColorAsState(
        targetValue = if (pressed) BrandPrimaryBorder else BorderDefault,
        animationSpec = tween(180),
        label = ""
    )

    Card(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier.fillMaxWidth(),
        shape = SaludifyRadius.cardLg,
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(SaludifyRadius.iconLg)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(22.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextPlaceholder
                )
            }

            if (badge != null) {
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clip(SaludifyRadius.badge)
                        .background(badgeBackground)
                        .padding(horizontal = 9.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = badge,
                        color = badgeTextColor,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 11.sp
                    )
                }
            }

            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = null,
                tint = if (pressed) BrandPrimary else TextPlaceholder
            )
        }
    }
}

// ── FAB Gemini ───────────────────────────────────────────────────────────────

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
