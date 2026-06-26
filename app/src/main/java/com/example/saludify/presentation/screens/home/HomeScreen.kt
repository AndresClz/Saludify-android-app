package com.example.saludify.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Turno
import com.example.saludify.domain.model.Usuario
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BorderMuted
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimaryDark
import com.example.saludify.ui.theme.BrandPrimaryLight
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticOrange
import com.example.saludify.ui.theme.SemanticOrangeSurface
import com.example.saludify.ui.theme.SemanticPurple
import com.example.saludify.ui.theme.SemanticPurpleSurface
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

@Composable
fun HomeScreen() {
    val usuario = MockData.currentUser ?: MockData.usuarios.first()
    val turno = MockData.proximoTurno

    Column(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        HomeHeader(usuario = usuario)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            CredencialesSection(usuario = usuario)
            TokenSection()
            ProximosTurnosSection(turno = turno)
            AccesosRapidosSection()
        }
    }
}

@Composable
private fun HomeHeader(usuario: Usuario) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(SaludifyRadius.full)
                    .background(BrandPrimary),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = usuario.nombre.first().toString(),
                    color = TextOnPrimary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 1.em
                )
            }
            Column {
                Text(
                    text = "Buenos días,",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    lineHeight = 1.3.em
                )
                Text(
                    text = "${usuario.nombre} ${usuario.apellido}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDefault,
                    letterSpacing = (-0.2).sp,
                    lineHeight = 1.3.em
                )
            }
        }
        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(SaludifyRadius.full)
                .background(BackgroundSubtle),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notificaciones",
                tint = TextMuted,
                modifier = Modifier.size(20.dp)
            )
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = (-2).dp, y = 2.dp)
                    .clip(SaludifyRadius.full)
                    .background(BackgroundSurface),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(SaludifyRadius.full)
                        .background(Color(0xFFEF4444))
                )
            }
        }
    }
    HorizontalDivider(color = BorderLight, thickness = 1.dp)
}

@Composable
private fun CredencialesSection(usuario: Usuario) {
    Column(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Tus credenciales",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                letterSpacing = (-0.1).sp
            )
            Text(text = "Ver todas", fontSize = 12.sp, color = BrandPrimary)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 10.dp, shape = SaludifyRadius.panel)
                .clip(SaludifyRadius.panel)
                .background(
                    brush = Brush.linearGradient(
                        colorStops = arrayOf(
                            0f to BrandPrimaryDark,
                            0.52f to BrandPrimary,
                            1f to BrandPrimaryLight
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    )
                )
        ) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .align(Alignment.TopEnd)
                    .offset(x = 38.dp, y = (-38).dp)
                    .clip(SaludifyRadius.full)
                    .background(Color.White.copy(alpha = 0.07f))
            )
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 24.dp, y = 20.dp)
                    .clip(SaludifyRadius.full)
                    .background(Color.White.copy(alpha = 0.05f))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 22.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 22.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Saludify",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextOnPrimary,
                        letterSpacing = (-0.2).sp
                    )
                    Box(
                        modifier = Modifier
                            .size(width = 32.dp, height = 22.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color.White.copy(alpha = 0.2f))
                            .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(5.dp))
                    )
                }
                Text(
                    text = "0000 · 0000 · 1234",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = TextOnPrimary,
                    letterSpacing = 0.2.em,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        Text(
                            text = "TITULAR",
                            fontSize = 9.sp,
                            color = TextOnPrimary.copy(alpha = 0.6f),
                            letterSpacing = 0.09.em,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        Text(
                            text = "${usuario.nombre} ${usuario.apellido}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextOnPrimary
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "PLAN",
                            fontSize = 9.sp,
                            color = TextOnPrimary.copy(alpha = 0.6f),
                            letterSpacing = 0.09.em,
                            modifier = Modifier.padding(bottom = 3.dp)
                        )
                        Text(
                            text = "Premium",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextOnPrimary
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 11.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(width = 20.dp, height = 5.dp)
                    .clip(SaludifyRadius.badge)
                    .background(BrandPrimary)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .clip(SaludifyRadius.badge)
                    .background(BorderMuted)
            )
        }
    }
}

@Composable
private fun TokenSection() {
    Column(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp)) {
        Text(
            text = "Token de acceso",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary,
            letterSpacing = (-0.1).sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = SaludifyRadius.cardLg)
                .clip(SaludifyRadius.cardLg)
                .background(BackgroundSurface)
                .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "842 619",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextDefault,
                    letterSpacing = 0.24.em,
                    lineHeight = 1.1.em
                )
                Row(modifier = Modifier.padding(top = 5.dp)) {
                    Text(
                        text = "Válido por ",
                        fontSize = 11.sp,
                        color = TextPlaceholder,
                        lineHeight = 1.4.em
                    )
                    Text(
                        text = "23:47 hs",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextSecondary,
                        lineHeight = 1.4.em
                    )
                    Text(
                        text = " · Tocá para copiar",
                        fontSize = 11.sp,
                        color = TextPlaceholder,
                        lineHeight = 1.4.em
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(SaludifyRadius.iconLg)
                    .background(BrandPrimarySurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Refrescar token",
                    tint = BrandPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun ProximosTurnosSection(turno: Turno) {
    Column(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Próximos turnos",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                letterSpacing = (-0.1).sp
            )
            Text(text = "Ver todos", fontSize = 12.sp, color = BrandPrimary)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = SaludifyRadius.cardLg)
                .clip(SaludifyRadius.cardLg)
                .background(BackgroundSurface)
                .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
                .padding(horizontal = 18.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier
                    .width(46.dp)
                    .clip(SaludifyRadius.iconLg)
                    .background(BrandPrimarySurface)
                    .padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = turno.mes,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary,
                    letterSpacing = 0.05.em,
                    lineHeight = 1.em
                )
                Text(
                    text = turno.dia,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPrimary,
                    lineHeight = 1.1.em
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = turno.medico,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = turno.especialidad,
                    fontSize = 12.sp,
                    color = TextMuted,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    TurnoChip(text = "${turno.diaSemana} · ${turno.hora}")
                    TurnoChip(text = turno.lugar)
                }
            }
        }
    }
}

@Composable
private fun TurnoChip(text: String) {
    Box(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(BackgroundSubtle)
            .padding(horizontal = 10.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = TextSecondary
        )
    }
}

@Composable
private fun AccesosRapidosSection() {
    Column(modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 18.dp)) {
        Text(
            text = "Accesos rápidos",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextSecondary,
            letterSpacing = (-0.1).sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickAccessCard(
                label = "Sacar turno",
                icon = Icons.Filled.Add,
                iconBackground = BrandPrimarySurface,
                iconTint = BrandPrimary,
                modifier = Modifier.weight(1f)
            )
            QuickAccessCard(
                label = "Mis turnos",
                icon = Icons.Filled.CalendarToday,
                iconBackground = SemanticOrangeSurface,
                iconTint = SemanticOrange,
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickAccessCard(
                label = "Reintegros",
                icon = Icons.Filled.ArrowDownward,
                iconBackground = SemanticSuccessSurface,
                iconTint = SemanticSuccess,
                modifier = Modifier.weight(1f)
            )
            QuickAccessCard(
                label = "Cartilla",
                icon = Icons.Filled.Apps,
                iconBackground = SemanticPurpleSurface,
                iconTint = SemanticPurple,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun QuickAccessCard(
    label: String,
    icon: ImageVector,
    iconBackground: Color,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .shadow(elevation = 1.dp, shape = SaludifyRadius.cardLg)
            .clip(SaludifyRadius.cardLg)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardLg)
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(iconBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier.size(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextDefault,
            lineHeight = 1.3.em
        )
    }
}
