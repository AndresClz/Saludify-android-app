package com.example.saludify.presentation.screens.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.BrandPrimarySurfaceStrong
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextPlaceholder

@Composable
fun OnboardingScreen(onAfiliadoClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BrandPrimarySurfaceStrong)
            .statusBarsPadding()
            .padding(top = 30.dp, start = 22.dp, end = 22.dp, bottom = 38.dp)
    ) {
        LogoRow()

        Spacer(Modifier.height(48.dp))

        Text(
            text = "Bienvenido/a",
            color = TextDefault,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = (28 * 1.2f).sp,
            letterSpacing = (-0.6).sp
        )
        Spacer(Modifier.height(10.dp))
        Text(
            text = "Gestioná tu cobertura médica desde el celular, sin filas ni llamados.",
            color = TextMuted,
            fontSize = 14.sp,
            lineHeight = (14 * 1.6f).sp
        )
        Spacer(Modifier.height(38.dp))

        OptionCard(
            iconContainerColor = BrandPrimarySurface,
            iconContent = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null,
                    tint = BrandPrimary,
                    modifier = Modifier.size(22.dp)
                )
            },
            title = "Soy afiliado",
            subtitle = "Ingresá con tu DNI",
            chevronTint = BrandPrimary,
            onClick = onAfiliadoClick
        )
        Spacer(Modifier.height(13.dp))
        OptionCard(
            iconContainerColor = BrandPrimarySurface,
            iconContent = {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null,
                    tint = BrandPrimary,
                    modifier = Modifier.size(18.dp)
                )
            },
            title = "Quiero afiliarme",
            subtitle = "Conocé nuestros planes",
            chevronTint = TextPlaceholder,
            onClick = {}
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = buildAnnotatedString {
                append("Al continuar aceptás los ")
                withStyle(SpanStyle(color = BrandPrimary)) { append("Términos y condiciones") }
                append("\ny la ")
                withStyle(SpanStyle(color = BrandPrimary)) { append("Política de privacidad") }
            },
            fontSize = 11.sp,
            color = TextPlaceholder,
            lineHeight = (11 * 1.7f).sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun LogoRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(BrandPrimary, RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "S",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 12.sp
            )
        }
        Text(
            text = "Saludify",
            color = BrandPrimary,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.3).sp
        )
    }
}

@Composable
private fun OptionCard(
    iconContainerColor: Color,
    iconContent: @Composable () -> Unit,
    title: String,
    subtitle: String,
    chevronTint: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.5.dp, BorderDefault),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(iconContainerColor, RoundedCornerShape(13.dp)),
                contentAlignment = Alignment.Center
            ) {
                iconContent()
            }
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                Text(
                    text = title,
                    color = TextDefault,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    color = TextPlaceholder,
                    fontSize = 12.sp
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = chevronTint,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}
