package com.example.saludify.presentation.screens.profile


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
import androidx.compose.foundation.border
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Update
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.em
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Output


//@Preview
@Composable
fun ProfileScreen() {

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
                    icon = Icons.Outlined.Person,
                    iconBackground = BrandPrimarySurface,
                    iconTint = BrandPrimary,
                    title = "Mis datos",
                    subtitle = "Informacion personal"
                )
            }

            item {
                AttentionMenuCard(
                    icon = Icons.Outlined.VerifiedUser,
                    iconBackground = BrandPrimarySurface,
                    iconTint = BrandPrimary,
                    title = "Gestionar Plan",
                    subtitle = "Cobertura y beneficios"
                )
            }

            item {
                AttentionMenuCard(
                    icon = Icons.Outlined.People,
                    iconBackground = BrandPrimarySurface,
                    iconTint = BrandPrimary,
                    title = "Grupo familiar",
                    subtitle = "Familiares afiliados"
                )
            }


            item {
                AttentionMenuCard(
                    icon = Icons.Outlined.AccessTime,
                    iconBackground = SemanticInfoSurface,
                    iconTint = SemanticInfo,
                    title = "Historia de consultas",
                    subtitle = "Mis atenciones anteriores"
                )
            }


            //Boton de Cerrar secion
            item {

                Card(
                    onClick = {
                        // Cerrar sesión
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = SaludifyRadius.cardLg,
                    colors = CardDefaults.cardColors(
                        containerColor = SemanticDangerSurface
                    ),
                    border = BorderStroke(
                        1.dp,
                        SemanticDangerBorder
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 1.dp
                    )
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp, vertical = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {

                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(SaludifyRadius.icon)
                                .background(SemanticDanger.copy(alpha = 0.10f)),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Outlined.Logout,
                                contentDescription = "Cerrar sesión",
                                tint = SemanticDanger,
                                modifier = Modifier.size(22.dp)
                            )

                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Text(
                            text = "Cerrar sesión",
                            color = SemanticDanger,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                    }

                }

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

            ProfileScreen()

        }

    }

}

@Composable
private fun AttentionHeroCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(175.dp)
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

        // círculos decorativos
        Box(
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = (-25).dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = .08f))
        )

        Box(
            modifier = Modifier
                .size(70.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 18.dp, y = 18.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = .05f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(.15f))
                        .border(
                            1.dp,
                            Color.White.copy(.35f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        "M",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )

                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {

                    Text(
                        text = "María González",
                        color = Color.White,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        HeroBadge("Plan Premium")

                        HeroBadge("DNI 12.345.678")

                    }

                }

            }

            Spacer(modifier = Modifier.weight(1f))

            HorizontalDivider(
                color = Color.White.copy(alpha = .20f)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                HeroInfo(
                    "AFILIADO DESDE",
                    "Enero 2018"
                )

                HeroInfo(
                    "PRÓXIMA CUOTA",
                    "Vence 30 Jun"
                )

                HeroInfo(
                    "GRUPO",
                    "3 integrantes"
                )

            }

        }

    }

}

@Composable
private fun HeroBadge(
    text: String
) {

    Box(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(Color.White.copy(.15f))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {

        Text(
            text = text,
            color = Color.White,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )

    }

}

@Composable
private fun HeroInfo(
    title: String,
    value: String
) {

    Column {

        Text(
            text = title,
            color = Color.White.copy(alpha = .65f),
            fontSize = 10.sp
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = value,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
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