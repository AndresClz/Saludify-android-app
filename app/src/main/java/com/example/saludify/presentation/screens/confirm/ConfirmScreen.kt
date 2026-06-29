package com.example.saludify.presentation.screens.confirm

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.Medico
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.SemanticSuccessSurface
import com.example.saludify.ui.theme.SemanticWarningIcon
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val StepperEmpty      = Color(0xFFE5E7EB)
private val StarEmpty         = Color(0xFFE5E7EB)
private val DetallesDivider   = Color(0xFFF3F4F6)
private val TipoChipShape     = RoundedCornerShape(13.dp)

private fun diaSemanaLabel(d: String) = when (d) {
    "Lun" -> "Lunes"; "Mar" -> "Martes"; "Mié" -> "Miércoles"
    "Jue" -> "Jueves"; "Vie" -> "Viernes"; "Sáb" -> "Sábado"; "Dom" -> "Domingo"
    else -> d
}

private fun mesLabel(m: String) = when (m) {
    "Ene" -> "enero"; "Feb" -> "febrero"; "Mar" -> "marzo"; "Abr" -> "abril"
    "May" -> "mayo"; "Jun" -> "junio"; "Jul" -> "julio"; "Ago" -> "agosto"
    "Sep" -> "septiembre"; "Oct" -> "octubre"; "Nov" -> "noviembre"; "Dic" -> "diciembre"
    else -> m
}

@Composable
fun ConfirmScreen(
    onBackClick: () -> Unit,
    onConfirmar: () -> Unit
) {
    val medico  = MockData.medicos.first()
    val usuario = MockData.currentUser ?: MockData.usuarios.first()

    Column(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        ConfirmHeader(onBackClick = onBackClick)

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp, vertical = 20.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Text(
                text = "Resumen del turno",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.4).sp,
                lineHeight = 1.2.em
            )

            DoctorCard(medico = medico)

            DetallesCard(
                medico = medico,
                nombrePaciente = "${usuario.nombre} ${usuario.apellido}",
                subtituloPaciente = "Titular · DNI ${formatDni(usuario.dni)}"
            )

            TipoChip(modalidad = medico.modalidad)

            Spacer(Modifier.height(8.dp))
        }

        // CTAs anclados al fondo
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundApp)
                .padding(start = 18.dp, end = 18.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(SaludifyRadius.button)
                    .background(BrandPrimary)
                    .clickable(onClick = onConfirmar)
                    .padding(vertical = 17.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Confirmar turno",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextOnPrimary
                )
            }
            Text(
                text = "Cancelar",
                fontSize = 13.sp,
                color = TextPlaceholder,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            )
        }
    }
}

// ── Header ─────────────────────────────────────────────────────────────────────

@Composable
private fun ConfirmHeader(onBackClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(SaludifyRadius.full)
                    .clickable(onClick = onBackClick),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = TextDefault,
                    modifier = Modifier.size(20.dp)
                )
            }
            Text(
                text = "Confirmar turno",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextSecondary,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.size(28.dp))
        }

        // Stepper: 4/4 llenos
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp)
                .padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(3.dp)
                        .clip(SaludifyRadius.badge)
                        .background(BrandPrimary)
                )
            }
        }

        HorizontalDivider(color = BorderLight, thickness = 1.dp)
    }
}

// ── Doctor card ────────────────────────────────────────────────────────────────

@Composable
private fun DoctorCard(medico: Medico) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = SaludifyRadius.cardXl,
                spotColor = BrandPrimary.copy(alpha = 0.08f)
            )
            .clip(SaludifyRadius.cardXl)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardXl)
            .padding(18.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(SaludifyRadius.full)
                .background(BrandPrimary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = iniciales(medico.nombre),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = TextOnPrimary,
                lineHeight = 1.em
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = medico.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                modifier = Modifier.padding(bottom = 2.dp)
            )
            Text(
                text = medico.especialidad,
                fontSize = 13.sp,
                color = TextMuted,
                modifier = Modifier.padding(bottom = 6.dp)
            )
            // Rating
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                    repeat(4) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = SemanticWarningIcon,
                            modifier = Modifier.size(12.dp)
                        )
                    }
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        tint = StarEmpty,
                        modifier = Modifier.size(12.dp)
                    )
                }
                Text(text = "4.8 · 124 opiniones", fontSize = 11.sp, color = TextMuted)
            }
        }
    }
}

// ── Detalles card ──────────────────────────────────────────────────────────────

@Composable
private fun DetallesCard(
    medico: Medico,
    nombrePaciente: String,
    subtituloPaciente: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 6.dp,
                shape = SaludifyRadius.cardXl,
                spotColor = BrandPrimary.copy(alpha = 0.08f)
            )
            .clip(SaludifyRadius.cardXl)
            .background(BackgroundSurface)
            .border(1.dp, BorderDefault, SaludifyRadius.cardXl)
    ) {
        // Fila 1 — Fecha y hora
        DetalleRow(divider = true) {
            // Badge calendario
            Column(
                modifier = Modifier
                    .size(38.dp)
                    .clip(SaludifyRadius.icon)
                    .background(BrandPrimarySurface),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = medico.mes.uppercase(),
                    fontSize = 8.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary,
                    lineHeight = 1.em
                )
                Text(
                    text = medico.dia,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPrimary,
                    lineHeight = 1.1.em
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Fecha y hora",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "${diaSemanaLabel(medico.diaSemana)} ${medico.dia} de ${mesLabel(medico.mes)} · ${medico.hora}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
            }
        }

        // Fila 2 — Lugar
        DetalleRow(divider = true) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(SaludifyRadius.icon)
                    .background(SemanticSuccessSurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = SemanticSuccess,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Lugar",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = medico.lugar,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
                if (medico.direccion.isNotEmpty()) {
                    Text(
                        text = "${medico.direccion}, CABA",
                        fontSize = 12.sp,
                        color = TextMuted
                    )
                }
            }
        }

        // Fila 3 — Paciente
        DetalleRow(divider = false) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(SaludifyRadius.full)
                    .background(BrandPrimarySurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = nombrePaciente.first().toString(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPrimary,
                    lineHeight = 1.em
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Paciente",
                    fontSize = 11.sp,
                    color = TextPlaceholder,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = nombrePaciente,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextDefault
                )
                Text(text = subtituloPaciente, fontSize = 12.sp, color = TextMuted)
            }
        }
    }
}

@Composable
private fun DetalleRow(divider: Boolean, content: @Composable () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = { content() }
        )
        if (divider) HorizontalDivider(color = DetallesDivider, thickness = 1.dp)
    }
}

// ── Tipo chip ──────────────────────────────────────────────────────────────────

@Composable
private fun TipoChip(modalidad: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(TipoChipShape)
            .background(BrandPrimarySurface)
            .padding(horizontal = 14.dp, vertical = 11.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(SaludifyRadius.full)
                .background(BrandPrimary)
        )
        Text(
            text = "Turno ${modalidad.lowercase()} · sin costo adicional",
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = BrandPrimary
        )
    }
}

// ── Helpers ────────────────────────────────────────────────────────────────────

private fun iniciales(nombre: String): String =
    nombre.split(" ").filter { it[0].isUpperCase() }.take(2).map { it[0] }.joinToString("")

private fun formatDni(dni: String): String =
    if (dni.length == 8) "${dni.take(2)}.${dni.drop(2).take(3)}.${dni.drop(5)}"
    else dni
