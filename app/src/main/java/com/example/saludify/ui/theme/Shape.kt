package com.example.saludify.ui.theme

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// M3 shape scheme — usado por componentes Material automáticamente
val SaludifyShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small      = RoundedCornerShape(11.dp),
    medium     = RoundedCornerShape(14.dp),
    large      = RoundedCornerShape(17.dp),
    extraLarge = RoundedCornerShape(20.dp)
)

// Tokens nombrados — usar en Composables para mayor expresividad
object SaludifyRadius {
    val badge         = RoundedCornerShape(99.dp)  // pills
    val full          = CircleShape                 // avatares
    val panel         = RoundedCornerShape(20.dp)  // cards de resumen
    val cardXl        = RoundedCornerShape(18.dp)  // card hero doctor
    val cardLg        = RoundedCornerShape(17.dp)  // items de menú, cards de médico
    val card          = RoundedCornerShape(14.dp)  // FAQ, teléfonos, sucursales
    val button        = RoundedCornerShape(14.dp)  // CTA primario
    val segmented     = RoundedCornerShape(12.dp)  // contenedor segmented control
    val buttonSm      = RoundedCornerShape(11.dp)  // botón Reservar inline
    val icon          = RoundedCornerShape(11.dp)  // contenedor ícono directorio
    val chip          = RoundedCornerShape(10.dp)  // chips de horario / filtros
    val segmentedItem = RoundedCornerShape(10.dp)  // opción activa en segmented
    val iconLg        = RoundedCornerShape(13.dp)  // ícono en listas de menú
    val iconMd        = RoundedCornerShape(9.dp)   // ícono dentro de cards
}
