package com.example.saludify.presentation.screens.profile

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.FamilyRestroom
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.saludify.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.*

@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProfileHeader()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 14.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { ProfileHeroCard() }
                item { Spacer(Modifier.height(4.dp)) }

                item {
                    ProfileMenuCard(
                        icon = Icons.Outlined.Person,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Mis datos",
                        subtitle = "Información personal"
                    )
                }
                item {
                    ProfileMenuCard(
                        icon = Icons.Outlined.VerifiedUser,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Gestionar Plan",
                        subtitle = "Cobertura y beneficios"
                    )
                }
                item {
                    ProfileMenuCard(
                        icon = Icons.Outlined.FamilyRestroom,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Grupo familiar",
                        subtitle = "Familiares afiliados",
                        badge = "3",
                        badgeBackground = BrandPrimarySurface,
                        badgeTextColor = BrandPrimary
                    )
                }
                item {
                    ProfileMenuCard(
                        icon = Icons.Outlined.Description,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Historial de consultas",
                        subtitle = "Mis atenciones anteriores"
                    )
                }

                item { LogoutCard() }
            }
        }

    }
}

@Composable
private fun ProfileHeader() {
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
            Image(
                painter = painterResource(R.drawable.ic_logo),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(BrandPrimary)
            )
            Text(
                text = "Mi perfil",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.3).sp
            )
        }
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(BackgroundSubtle),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = "Configuración",
                tint = TextMuted,
                modifier = Modifier.size(18.dp)
            )
        }
    }
    HorizontalDivider(color = BorderLight)
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewProfile() {
    SaludifyTheme {
        Surface {
            ProfileScreen()
        }
    }
}

@Composable
private fun ProfileHeroCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp, shape = SaludifyRadius.panel)
            .clip(SaludifyRadius.panel)
            .background(
                Brush.linearGradient(
                    colors = listOf(BrandPrimaryDark, BrandPrimary, BrandPrimaryLight)
                )
            )
    ) {
        // Círculos decorativos
        Box(
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.TopEnd)
                .offset(x = 30.dp, y = (-30).dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.07f))
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-20).dp, y = 20.dp)
                .clip(CircleShape)
                .background(Color.White.copy(alpha = 0.05f))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.22f))
                        .border(2.dp, Color.White.copy(alpha = 0.4f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "M",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 26.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = "María González",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.3).sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                        HeroBadge(text = "Plan Premium", backgroundAlpha = 0.22f)
                        HeroBadge(text = "DNI 12.345.678", backgroundAlpha = 0.12f)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Color.White.copy(alpha = 0.15f))
            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HeroInfo(label = "AFILIADO DESDE", value = "Enero 2018")
                HeroInfo(
                    label = "PRÓXIMA CUOTA",
                    value = "Vence 30 Jun",
                    valueColor = Color(0xFFFDE68A)
                )
                HeroInfo(label = "GRUPO", value = "3 integrantes")
            }
        }
    }
}

@Composable
private fun HeroBadge(text: String, backgroundAlpha: Float = 0.18f) {
    Box(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(Color.White.copy(alpha = backgroundAlpha))
            .padding(horizontal = 10.dp, vertical = 4.dp)
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
    label: String,
    value: String,
    valueColor: Color = Color.White
) {
    Column {
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.55f),
            fontSize = 10.sp,
            letterSpacing = 0.08.em
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            color = valueColor,
            fontWeight = FontWeight.SemiBold,
            fontSize = 13.sp
        )
    }
}

@Composable
private fun ProfileMenuCard(
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
    var pressed by remember { mutableStateOf(false) }

    val backgroundColor by animateColorAsState(
        targetValue = if (pressed) BrandPrimarySurface else BackgroundSurface,
        animationSpec = tween(durationMillis = if (pressed) 40 else 200),
        label = ""
    )
    val borderColor by animateColorAsState(
        targetValue = if (pressed) BrandPrimaryBorder else BorderDefault,
        animationSpec = tween(durationMillis = if (pressed) 40 else 200),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = SaludifyRadius.cardLg)
            .clip(SaludifyRadius.cardLg)
            .background(backgroundColor)
            .border(1.dp, borderColor, SaludifyRadius.cardLg)
            .pointerInput(onClick) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        try { tryAwaitRelease() } finally { pressed = false }
                    },
                    onTap = { onClick() }
                )
            }
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

@Composable
private fun LogoutCard(onClick: () -> Unit = {}) {
    var pressed by remember { mutableStateOf(false) }
    val bgColor by animateColorAsState(
        targetValue = if (pressed) SemanticDangerSurfaceStrong else SemanticDangerSurface,
        animationSpec = tween(durationMillis = if (pressed) 40 else 200),
        label = ""
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 1.dp, shape = SaludifyRadius.cardLg)
            .clip(SaludifyRadius.cardLg)
            .background(bgColor)
            .border(1.dp, SemanticDangerBorder, SaludifyRadius.cardLg)
            .pointerInput(onClick) {
                detectTapGestures(
                    onPress = {
                        pressed = true
                        try { tryAwaitRelease() } finally { pressed = false }
                    },
                    onTap = { onClick() }
                )
            }
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
                    .background(SemanticDangerSurfaceStrong),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Logout,
                    contentDescription = null,
                    tint = SemanticDanger,
                    modifier = Modifier.size(22.dp)
                )
            }
            Spacer(modifier = Modifier.width(14.dp))
            Text(
                text = "Cerrar sesión",
                color = SemanticDanger,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

