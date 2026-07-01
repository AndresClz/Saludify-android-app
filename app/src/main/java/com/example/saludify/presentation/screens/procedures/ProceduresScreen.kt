package com.example.saludify.presentation.screens.procedures

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimaryBorder
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticDanger
import com.example.saludify.ui.theme.SemanticDangerBorder
import com.example.saludify.ui.theme.SemanticInfo
import com.example.saludify.ui.theme.SemanticInfoSurface
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.SemanticWarning
import com.example.saludify.ui.theme.SemanticWarningBorder
import com.example.saludify.ui.theme.SemanticWarningSurface
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder



@Composable
fun StatusItem(
    modifier: Modifier = Modifier,
    numberColor: Color,
    borderColor: Color,
    number: String,
    title: String,
    backgroundColor: Color
) {

    Box(
        modifier = modifier
            .height(84.dp)
            .border(
                width = 1.5.dp,
                color = borderColor,
                shape = RoundedCornerShape(15.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(15.dp)
    ) {

        Column {
            Text(
                text = number,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = numberColor
            )

            Spacer(modifier = Modifier.height(1.dp))

            Text(
                text = title,
                fontSize = 12.sp,
                color = Color(0xFF6B7280)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = BrandPrimaryBorder
            )
        }
    }
}
//------------------------------------
@Composable
private fun StatusCard(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .shadow(elevation = 8.dp, shape = SaludifyRadius.panel)
            .clip(SaludifyRadius.panel)
            .background(color = BackgroundSurface) )
            {
                Column(
                    modifier = Modifier.padding(24.dp)
                ) {

                    Text(
                        text = "Estado de tus trámites",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPlaceholder
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                Row(
                modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        StatusItem(title = "en proceso", number = "2", modifier = Modifier.weight(1f), numberColor = SemanticInfo, borderColor = SemanticInfo, backgroundColor = SemanticInfoSurface)
        StatusItem(title = "por vencer",number = "1", modifier = Modifier.weight(1f), numberColor = SemanticWarning, borderColor = SemanticWarning, backgroundColor = SemanticWarningSurface)
        StatusItem(title = "resueltos",number = "5", modifier = Modifier.weight(1f), numberColor = SemanticSuccess, borderColor = SemanticSuccess, backgroundColor = SemanticSuccessSurface)
    } }

            }
            }
//------------------------------------

@Composable
 fun ProceduresMenuCard(
    icon: ImageVector,
    iconBackground: Color,
    iconTint: Color,
    badge: String? = null,
    badgeBackground: Color = SemanticWarningBorder,
    badgeTextColor: Color = SemanticWarning,
    title: String,
    onClick: () -> Unit = {},
    subtitle: String
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


//------------------------------------
@Composable
fun ProceduresHeader() {
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


//----------------------------------- gemini

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

//-------------------------------------

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
                        icon = Icons.Outlined.Search,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Autorizaciones",
                        badge = "2",
                        subtitle = "Autorizá trámites, estudios y turnos médicos"
                    )
    }

                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.Search,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Reintegros",
                        subtitle = "Procesá y consultá tus reintegros médicos."
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Outlined.Search,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Presupuestos",
                        subtitle = "Conocé el costo estimado de tus prestaciones."
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon = Icons.Filled.Description,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Facturas",
                        subtitle = "Consultá y descargá tus facturas emitidas."
                    )
                }
                item {
                    ProceduresMenuCard(
                        icon =  Icons.Filled.CreditCard,
                        iconBackground = BrandPrimarySurface,
                        iconTint = BrandPrimary,
                        title = "Pagos",
                        subtitle = "Revisá el estado y realizá tus pagos.",
                        badge = "vence pronto",
                        badgeBackground = SemanticDangerBorder,
                        badgeTextColor = SemanticDanger
                    )
                }
            }
        }
        GeminiFab(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 74.dp) )
    }
}


