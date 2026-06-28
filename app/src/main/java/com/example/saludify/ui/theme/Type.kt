@file:OptIn(androidx.compose.ui.text.ExperimentalTextApi::class)

package com.example.saludify.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.saludify.R

val DmSans = FontFamily(
    Font(R.font.dm_sans, weight = FontWeight.Light,      variationSettings = FontVariation.Settings(FontVariation.weight(300))),
    Font(R.font.dm_sans, weight = FontWeight.Normal,     variationSettings = FontVariation.Settings(FontVariation.weight(400))),
    Font(R.font.dm_sans, weight = FontWeight.Medium,     variationSettings = FontVariation.Settings(FontVariation.weight(500))),
    Font(R.font.dm_sans, weight = FontWeight.SemiBold,   variationSettings = FontVariation.Settings(FontVariation.weight(600))),
    Font(R.font.dm_sans, weight = FontWeight.Bold,       variationSettings = FontVariation.Settings(FontVariation.weight(700))),
    Font(R.font.dm_sans, weight = FontWeight.ExtraBold,  variationSettings = FontVariation.Settings(FontVariation.weight(800))),
    Font(R.font.dm_sans, weight = FontWeight.Black,      variationSettings = FontVariation.Settings(FontVariation.weight(900))),
)

val Typography = Typography(
    // 32sp Bold — inicial de avatar en Perfil
    displayLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp,
        lineHeight = 38.sp,
        letterSpacing = (-0.5).sp
    ),
    // 26sp Bold — títulos display (¿Para quién?, Turno confirmado)
    displayMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    // 22sp Bold — títulos de flujo (Confirmar turno, Buscador)
    displaySmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.3).sp
    ),
    // 20sp Bold — nombre en Perfil
    headlineLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = (-0.3).sp
    ),
    // 18sp Bold — títulos de sección medianos
    headlineMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    // 16sp SemiBold — título de pantalla en header sticky
    headlineSmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    // 15sp SemiBold — nombres de médico, títulos de card, CTA
    titleLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),
    // 14sp SemiBold — labels de items de lista
    titleMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // 13sp SemiBold — tabs activos, chips, CTAs secundarios
    titleSmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    // 14sp Regular — contenido principal
    bodyLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),
    // 12sp Regular — subtítulos, descripciones cortas
    bodyMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    // 11sp Regular — metadata secundaria, horarios
    bodySmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // 15sp SemiBold — label de botón CTA primario
    labelLarge = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // 11sp SemiBold — texto de badge
    labelMedium = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.sp
    ),
    // 10sp SemiBold — labels de sección en caps
    labelSmall = TextStyle(
        fontFamily = DmSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 10.sp,
        lineHeight = 14.sp,
        letterSpacing = 0.9.sp
    ),
)
