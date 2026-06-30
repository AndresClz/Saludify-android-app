package com.example.saludify.presentation.screens.search

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextDisabled
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val SectionLabelColor = Color(0xFF8896AA)

private val especialidadesFrecuentes = listOf(
    "Cardiología", "Clínica Médica", "Dermatología", "Pediatría"
)

@Composable
fun SearchScreen(
    onNext: () -> Unit
) {
    val usuario = MockData.currentUser ?: MockData.usuarios.first()

    var searchText by remember { mutableStateOf("") }
    var selectedEspecialidad by remember { mutableStateOf<String?>(null) }

    val chipsEnabled = searchText.isBlank()
    val canContinue  = searchText.isNotBlank() || selectedEspecialidad != null

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundApp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 18.dp)
        ) {
            Text(
                text = "¿Qué especialidad\no médico buscás?",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.4).sp,
                lineHeight = 1.25.em,
                modifier = Modifier.padding(top = 22.dp)
            )

            // Search bar
            SearchField(
                value = searchText,
                chipSelected = selectedEspecialidad != null,
                onValueChange = { text ->
                    searchText = text
                    if (text.isNotBlank()) selectedEspecialidad = null
                },
                onFocusGained = { selectedEspecialidad = null },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            // Chips de filtro modalidad (decorativos, no interactivos en este flujo)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(label = "Todos",      active = true)
                FilterChip(label = "Online",     active = false)
                FilterChip(label = "Presencial", active = false)
            }

            // Fila ubicación
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(SaludifyRadius.full)
                        .background(TextPlaceholder)
                )
                Text(
                    text = "Buenos Aires, CABA",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = TextDefault,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = "Cambiar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary
                )
            }

            // Especialidades frecuentes
            Text(
                text = "ESPECIALIDADES FRECUENTES",
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                color = SectionLabelColor,
                letterSpacing = 0.09.em,
                modifier = Modifier.padding(top = 22.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(9.dp)
            ) {
                especialidadesFrecuentes.chunked(2).forEach { fila ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(9.dp)
                    ) {
                        fila.forEach { label ->
                            EspecialidadChip(
                                label = label,
                                selected = selectedEspecialidad == label,
                                dimmed = !chipsEnabled && selectedEspecialidad != label,
                                onClick = {
                                    selectedEspecialidad =
                                        if (selectedEspecialidad == label) null else label
                                    searchText = ""
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        // Padding si la fila tiene un solo elemento
                        if (fila.size < 2) Spacer(Modifier.weight(1f))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }

        // CTA "Continuar"
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundApp)
                .padding(horizontal = 18.dp, vertical = 28.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(SaludifyRadius.button)
                    .background(if (canContinue) BrandPrimary else BorderDefault)
                    .then(
                        if (canContinue) Modifier.clickable(onClick = onNext)
                        else Modifier
                    )
                    .padding(vertical = 17.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Continuar",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = if (canContinue) TextOnPrimary else TextDisabled
                )
            }
        }
    }
}

// ── Search field ──────────────────────────────────────────────────────────────

@Composable
private fun SearchField(
    value: String,
    chipSelected: Boolean,
    onValueChange: (String) -> Unit,
    onFocusGained: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Visualmente "inactivo" cuando hay chip seleccionado y no hay texto
    val dormant    = chipSelected && value.isEmpty()
    val borderColor = if (dormant) BorderDefault else BrandPrimary
    val borderWidth = if (dormant) 1.5.dp else 2.dp
    val bgColor     = if (dormant) BackgroundSubtle else BackgroundSurface
    val iconTint    = if (dormant) TextPlaceholder else BrandPrimary

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = TextDefault
        ),
        cursorBrush = SolidColor(BrandPrimary),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        modifier = modifier
            .onFocusChanged { if (it.isFocused) onFocusGained() }
            .shadow(
                elevation = if (dormant) 0.dp else 4.dp,
                shape = SaludifyRadius.card,
                spotColor = BrandPrimary.copy(alpha = 0.10f)
            )
            .clip(SaludifyRadius.card)
            .background(bgColor)
            .border(borderWidth, borderColor, SaludifyRadius.card),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(18.dp)
                )
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Ej: Cardiología, Dr. Silva…",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextPlaceholder
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}

// ── Chips ─────────────────────────────────────────────────────────────────────

@Composable
private fun FilterChip(label: String, active: Boolean) {
    Box(
        modifier = Modifier
            .clip(SaludifyRadius.badge)
            .background(if (active) BrandPrimary else BackgroundSubtle)
            .padding(horizontal = 14.dp, vertical = 8.dp)
    ) {
        Text(
            text = label,
            fontSize = 13.sp,
            fontWeight = if (active) FontWeight.SemiBold else FontWeight.Medium,
            color = if (active) TextOnPrimary else TextMuted
        )
    }
}

@Composable
private fun EspecialidadChip(
    label: String,
    selected: Boolean,
    dimmed: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val bgColor     = if (selected) BrandPrimary else if (dimmed) BackgroundSubtle else BackgroundSurface
    val textColor   = if (selected) TextOnPrimary else if (dimmed) TextDisabled else TextSecondary
    val borderColor = if (selected) BrandPrimary else BorderDefault

    Box(
        modifier = modifier
            .clip(SaludifyRadius.chip)
            .background(bgColor)
            .border(1.dp, borderColor, SaludifyRadius.chip)
            .clickable(onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = textColor
        )
    }
}
