package com.example.saludify.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val SaludifyColorScheme = lightColorScheme(
    primary                = BrandPrimary,
    onPrimary              = TextOnPrimary,
    primaryContainer       = BrandPrimarySurface,
    onPrimaryContainer     = BrandPrimaryDark,

    secondary              = SemanticInfo,
    onSecondary            = TextOnPrimary,
    secondaryContainer     = SemanticInfoSurface,
    onSecondaryContainer   = SemanticInfo,

    tertiary               = SemanticSuccess,
    onTertiary             = TextOnPrimary,
    tertiaryContainer      = SemanticSuccessSurface,
    onTertiaryContainer    = SemanticSuccess,

    error                  = SemanticDanger,
    onError                = TextOnPrimary,
    errorContainer         = SemanticDangerSurface,
    onErrorContainer       = SemanticDanger,

    background             = BackgroundApp,
    onBackground           = TextDefault,

    surface                = BackgroundSurface,
    onSurface              = TextDefault,
    surfaceVariant         = BackgroundSubtle,
    onSurfaceVariant       = TextMuted,

    outline                = BorderDefault,
    outlineVariant         = BorderLight,
)

@Composable
fun SaludifyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SaludifyColorScheme,
        typography  = Typography,
        shapes      = SaludifyShapes,
        content     = content
    )
}
