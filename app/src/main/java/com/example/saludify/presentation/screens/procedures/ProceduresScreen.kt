package com.example.saludify.presentation.screens.procedures

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.Calculate
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.example.saludify.R
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.*

@Composable
fun ProceduresScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            ProceduresHeader()
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 16.dp, bottom = 90.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { StatusCard() }
                item { Spacer(Modifier.height(8.dp)) }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.AssignmentTurnedIn,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Autorizaciones",
                        subtitle = "Solicitar o consultar estado",
                        badge = "2",
                        badgeBackground = SemanticWarningSurfaceStrong,
                        badgeTextColor = SemanticWarning
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.AccountBalance,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Reintegros",
                        subtitle = "Cargar gastos para reintegro"
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.Calculate,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Presupuestos",
                        subtitle = "Cotizaciones de prácticas"
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.Receipt,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Facturas",
                        subtitle = "Comprobantes de pago"
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.CreditCard,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Pagos",
                        subtitle = "Cuotas y medios de pago",
                        badge = "Vence hoy",
                        badgeBackground = SemanticDangerSurfaceStrong,
                        badgeTextColor = SemanticDanger
                    )
                }
            }
        }
    }
}

@Composable
private fun ProceduresHeader() {
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
                text = "Trámites",
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

@Composable
private fun StatusCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = SaludifyRadius.panel)
            .clip(SaludifyRadius.panel)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.panel)
            .padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Estado de tus trámites",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = TextPlaceholder
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StatusChip(
                modifier = Modifier.weight(1f),
                number = "2",
                label = "En proceso",
                background = BrandPrimarySurfaceStrong,
                borderColor = BrandPrimaryBorder,
                numberColor = BrandPrimary
            )
            StatusChip(
                modifier = Modifier.weight(1f),
                number = "1",
                label = "Por vencer",
                background = SemanticWarningSurface,
                borderColor = SemanticWarningBorder,
                numberColor = SemanticWarning
            )
            StatusChip(
                modifier = Modifier.weight(1f),
                number = "5",
                label = "Resueltos",
                background = SemanticSuccessSurface,
                borderColor = Color(0xFFBBF7D0),
                numberColor = SemanticSuccess
            )
        }
    }
}

@Composable
private fun StatusChip(
    modifier: Modifier = Modifier,
    number: String,
    label: String,
    background: Color,
    borderColor: Color,
    numberColor: Color
) {
    Column(
        modifier = modifier
            .clip(SaludifyRadius.card)
            .background(background)
            .border(1.dp, borderColor, SaludifyRadius.card)
            .padding(horizontal = 10.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = number,
            fontSize = 22.sp,
            fontWeight = FontWeight.ExtraBold,
            color = numberColor,
            lineHeight = 1.em
        )
        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = TextPlaceholder
        )
    }
}

@Composable
private fun ProceduresMenuCard(
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
