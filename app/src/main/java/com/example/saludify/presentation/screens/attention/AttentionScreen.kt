package com.example.saludify.presentation.screens.attention

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.*


import androidx.compose.foundation.BorderStroke
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon

import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.em


@Preview
@Composable

fun AttentionScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {

        AttentionHeader()

        Column(
            modifier = Modifier
                .padding(18.dp)
        ) {

            AttentionHeroCard()

            Spacer(modifier = Modifier.height(18.dp))

        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

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
                    subtitle = "Plan y prestaciones incluidas"
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
                    iconBackground = SemanticWarningSurface,
                    iconTint = SemanticWarning,
                    title = "Mis turnos",
                    subtitle = "Próximos · Cancelar",
                    badge = "1"
                )
            }

            item {
                AttentionMenuCard(
                    icon = Icons.Outlined.Description,
                    iconBackground = SemanticInfoSurface,
                    iconTint = SemanticInfo,
                    title = "Historia de consultas",
                    subtitle = "Mis atenciones anteriores"
                )
            }

            item {
                Spacer(Modifier.height(90.dp))
            }

        }

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
                text = "Atención",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault
            )
        }



    }

    HorizontalDivider(
        color = BorderLight
    )

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
            .shadow(
                elevation = 8.dp,
                shape = SaludifyRadius.panel
            )
            .clip(SaludifyRadius.panel)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        BrandPrimaryDark,
                        BrandPrimary,
                        BrandPrimaryLight
                    )
                )
            )
    ) {

        // Círculos decorativos
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
                .padding(22.dp)
        ) {

            Text(
                text = "Atención médica",
                color = TextOnPrimary.copy(alpha = 0.75f),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "¿Qué necesitás hoy?",
                color = TextOnPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                HeroChip(
                    text = "Turno online",
                    dotColor = SemanticSuccess
                )

                HeroChip(
                    text = "Urgencias",
                    dotColor = SemanticDanger
                )

            }

        }

    }

}

@Composable
private fun HeroChip(
    text: String,
    dotColor: Color
) {

    Row(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(Color.White.copy(alpha = 0.15f))
            .padding(horizontal = 14.dp, vertical = 9.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(dotColor)
        )

        Spacer(modifier = Modifier.width(8.dp))

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
    onClick: () -> Unit = {}
) {

    val interactionSource = remember {
        MutableInteractionSource()
    }

    val pressed by interactionSource.collectIsPressedAsState()

    val backgroundColor by animateColorAsState(
        targetValue =
            if (pressed)
                BrandPrimarySurface
            else
                BackgroundSurface,
        animationSpec = tween(180),
        label = ""
    )

    val borderColor by animateColorAsState(
        targetValue =
            if (pressed)
                BrandPrimaryBorder
            else
                BorderDefault,
        animationSpec = tween(180),
        label = ""
    )

    Card(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier.fillMaxWidth(),
        shape = SaludifyRadius.cardLg,
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(
            1.dp,
            borderColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(SaludifyRadius.icon)
                    .background(iconBackground),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(24.dp)
                )

            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = subtitle,
                    fontSize = 12.sp,
                    color = TextMuted
                )

            }

            if (badge != null) {

                Box(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(SemanticDanger),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = badge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
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