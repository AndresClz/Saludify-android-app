package com.example.saludify.presentation.screens.help

import android.content.Intent
import android.net.Uri
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.data.MockData
import com.example.saludify.domain.model.ContactoTelefono
import com.example.saludify.domain.model.FaqItem
import com.example.saludify.domain.model.Sucursal
import com.example.saludify.ui.theme.BackgroundApp
import com.example.saludify.ui.theme.BackgroundSegmented
import com.example.saludify.ui.theme.BackgroundSubtle
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BorderLight
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.SemanticDanger
import com.example.saludify.ui.theme.SemanticDangerSurfaceStrong
import com.example.saludify.ui.theme.SemanticSuccess
import com.example.saludify.ui.theme.TextDefault
import com.example.saludify.ui.theme.TextMuted
import com.example.saludify.ui.theme.TextOnPrimary
import com.example.saludify.ui.theme.TextPlaceholder
import com.example.saludify.ui.theme.TextSecondary

private val SectionLabelColor = Color(0xFF8896AA)

@Composable
fun HelpScreen() {
    var expandedFaqIndex by remember { mutableIntStateOf(0) }
    var contactTabIndex by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.fillMaxSize().background(BackgroundApp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            HelpScreenHeader()
            HelpSearchBar()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 18.dp)
                    .padding(top = 20.dp, bottom = 80.dp),
                verticalArrangement = Arrangement.spacedBy(26.dp)
            ) {
                AtencionInmediataSection()
                PreguntasFrecuentesSection(
                    faqs = MockData.faqItems,
                    expandedIndex = expandedFaqIndex,
                    onFaqClick = { i ->
                        expandedFaqIndex = if (expandedFaqIndex == i) -1 else i
                    }
                )
                ContactoSection(
                    selectedTab = contactTabIndex,
                    onTabSelected = { contactTabIndex = it },
                    telefonos = MockData.telefonos,
                    sucursales = MockData.sucursales
                )
            }
        }
        HelpGeminiFab(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 74.dp)
        )
    }
}

// ── Header ──────────────────────────────────────────────────────────────────

@Composable
private fun HelpScreenHeader() {
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
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
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
                text = "Ayuda",
                modifier = Modifier.weight(1f),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.3).sp
            )
            Spacer(modifier = Modifier.size(24.dp))
        }
        HorizontalDivider(color = BorderLight)
    }
}

// ── Search bar ───────────────────────────────────────────────────────────────

@Composable
private fun HelpSearchBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundApp)
            .padding(start = 18.dp, end = 18.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 2.dp, shape = SaludifyRadius.card)
                .border(width = 1.5.dp, color = BorderDefault, shape = SaludifyRadius.card)
                .clip(SaludifyRadius.card)
                .background(BackgroundSurface)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = TextPlaceholder,
                modifier = Modifier.size(16.dp)
            )
            Text(
                text = "Buscar en ayuda…",
                fontSize = 14.sp,
                color = TextPlaceholder
            )
        }
    }
}

// ── Section header ───────────────────────────────────────────────────────────

@Composable
private fun SectionHeader(label: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = label.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.SemiBold,
            color = SectionLabelColor,
            letterSpacing = 0.1.em
        )
        HorizontalDivider(
            modifier = Modifier.weight(1f),
            color = Color(0xFFDDE3EC)
        )
    }
}

// ── Sección 1: Atención inmediata ─────────────────────────────────────────────

@Composable
private fun AtencionInmediataSection() {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionHeader("Atención inmediata")
        ChatIACard()
    }
}

@Composable
private fun ChatIACard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = SaludifyRadius.cardXl,
                spotColor = BrandPrimary.copy(alpha = 0.26f)
            )
            .clip(SaludifyRadius.cardXl)
            .background(
                Brush.linearGradient(
                    colorStops = arrayOf(
                        0f to Color(0xFF0F3D8A),
                        0.55f to Color(0xFF1A5FCE),
                        1f to Color(0xFF2E7DE0)
                    )
                )
            )
    ) {
        // Decorative circle
        Box(
            modifier = Modifier
                .size(90.dp)
                .align(Alignment.TopEnd)
                .offset(x = 24.dp, y = (-24).dp)
                .background(Color.White.copy(alpha = 0.07f), CircleShape)
        )
        // Content
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Chat icon container
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .background(Color.White.copy(alpha = 0.16f), SaludifyRadius.iconLg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Chat,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            // Text
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Chat con IA · 24/7",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = (-0.2).sp
                )
                Spacer(Modifier.height(3.dp))
                Text(
                    text = "Sin esperas, respuesta inmediata",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.65f),
                    lineHeight = 17.sp
                )
            }
            // CTA button
            Row(
                modifier = Modifier
                    .background(BackgroundSurface, SaludifyRadius.chip)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(7.dp)
                        .background(SemanticSuccess, CircleShape)
                )
                Text(
                    text = "Iniciar",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = BrandPrimary
                )
            }
        }
    }
}

// ── Sección 2: Preguntas frecuentes ──────────────────────────────────────────

@Composable
private fun PreguntasFrecuentesSection(
    faqs: List<FaqItem>,
    expandedIndex: Int,
    onFaqClick: (Int) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionHeader("Preguntas frecuentes")
        Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
            faqs.forEachIndexed { index, faq ->
                FaqCard(
                    faq = faq,
                    isExpanded = expandedIndex == index,
                    onClick = { onFaqClick(index) }
                )
            }
            // "Ver todas" link
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ver todas las preguntas frecuentes",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = BrandPrimary
                )
                Spacer(Modifier.width(6.dp))
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = BrandPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun FaqCard(
    faq: FaqItem,
    isExpanded: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = SaludifyRadius.card)
            .clip(SaludifyRadius.card)
            .background(BackgroundSurface)
            .border(width = 1.dp, color = BorderDefault, shape = SaludifyRadius.card)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                text = faq.pregunta,
                modifier = Modifier.weight(1f),
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextDefault,
                lineHeight = 18.sp
            )
            Icon(
                imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown
                              else Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = if (isExpanded) BrandPrimary else TextPlaceholder,
                modifier = Modifier.size(20.dp)
            )
        }
        if (isExpanded) {
            HorizontalDivider(color = BackgroundSubtle)
            Text(
                text = faq.respuesta,
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, bottom = 13.dp, top = 10.dp),
                fontSize = 12.sp,
                color = TextMuted,
                lineHeight = 19.sp
            )
        }
    }
}

// ── Sección 3: Contacto ───────────────────────────────────────────────────────

@Composable
private fun ContactoSection(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    telefonos: List<ContactoTelefono>,
    sucursales: List<Sucursal>
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionHeader("Contacto")
        // Segmented control
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(BackgroundSegmented, SaludifyRadius.segmented)
                .padding(3.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            listOf("Teléfonos útiles", "Sucursales").forEachIndexed { index, label ->
                val isActive = selectedTab == index
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .then(
                            if (isActive) Modifier
                                .shadow(elevation = 2.dp, shape = SaludifyRadius.segmentedItem)
                                .clip(SaludifyRadius.segmentedItem)
                                .background(BackgroundSurface)
                            else Modifier
                                .clip(SaludifyRadius.segmentedItem)
                        )
                        .clickable { onTabSelected(index) }
                        .padding(vertical = 9.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = label,
                        fontSize = 13.sp,
                        fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Normal,
                        color = if (isActive) TextDefault else TextMuted
                    )
                }
            }
        }
        // Tab content
        if (selectedTab == 0) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                telefonos.forEach { contacto ->
                    TelefonoCard(contacto)
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                UbicacionBanner()
                sucursales.forEach { sucursal ->
                    SucursalCard(sucursal)
                }
            }
        }
    }
}

@Composable
private fun TelefonoCard(contacto: ContactoTelefono) {
    val iconBg = if (contacto.esUrgencias) SemanticDangerSurfaceStrong else BrandPrimarySurface
    val iconTint = if (contacto.esUrgencias) SemanticDanger else BrandPrimary
    val badgeBg = if (contacto.esUrgencias) SemanticDangerSurfaceStrong else BackgroundSubtle
    val badgeText = if (contacto.esUrgencias) BrandPrimary else TextMuted

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp, shape = SaludifyRadius.card)
            .clip(SaludifyRadius.card)
            .background(BackgroundSurface)
            .border(width = 1.dp, color = BorderDefault, shape = SaludifyRadius.card)
            .padding(horizontal = 15.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(38.dp)
                .background(iconBg, SaludifyRadius.icon),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(18.dp)
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = contacto.label,
                fontSize = 11.sp,
                color = TextPlaceholder
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = contacto.numero,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.2).sp
            )
        }
        Box(
            modifier = Modifier
                .background(badgeBg, SaludifyRadius.badge)
                .padding(horizontal = 10.dp, vertical = 4.dp)
        ) {
            Text(
                text = contacto.badgeText,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = badgeText
            )
        }
    }
}

@Composable
private fun UbicacionBanner() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(BrandPrimarySurface, SaludifyRadius.iconLg)
            .padding(horizontal = 14.dp, vertical = 11.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .background(BrandPrimary, SaludifyRadius.full),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
        }
        Text(
            text = "Ordenadas por distancia desde tu ubicación actual",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = BrandPrimary,
            lineHeight = 17.sp
        )
    }
}

@Composable
private fun SucursalCard(sucursal: Sucursal) {
    val context = LocalContext.current
    val cardBorderWidth = if (sucursal.esMasCercana) 1.5.dp else 1.dp
    val cardBorderColor = if (sucursal.esMasCercana) BrandPrimary else BorderDefault
    val cardShadowColor = if (sucursal.esMasCercana) BrandPrimary.copy(alpha = 0.12f)
                          else Color(0xFF000000).copy(alpha = 0.04f)
    val statusDotColor = if (sucursal.estaAbierto) SemanticSuccess else TextPlaceholder
    val statusTextColor = if (sucursal.estaAbierto) SemanticSuccess else TextPlaceholder

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = if (sucursal.esMasCercana) 4.dp else 2.dp,
                    shape = SaludifyRadius.cardLg, spotColor = cardShadowColor)
            .border(cardBorderWidth, cardBorderColor, SaludifyRadius.cardLg)
            .clip(SaludifyRadius.cardLg)
            .background(BackgroundSurface)
    ) {
        // ── Header de distancia ──
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (sucursal.esMasCercana) BrandPrimary else BackgroundSubtle)
                .padding(horizontal = 16.dp, vertical = 7.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (sucursal.esMasCercana) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(SemanticSuccess, SaludifyRadius.full)
                    )
                    Text(
                        text = "Más cercana · ${sucursal.distanciaTexto}",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
                Text(
                    text = sucursal.tiempoEstimado,
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.7f)
                )
            } else {
                Text(
                    text = sucursal.distanciaTexto,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary
                )
                Text(
                    text = sucursal.tiempoEstimado,
                    fontSize = 11.sp,
                    color = TextPlaceholder
                )
            }
        }
        // ── Cuerpo ──
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {
            Text(
                text = sucursal.nombre,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = TextDefault,
                letterSpacing = (-0.2).sp
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${sucursal.direccion}\n${sucursal.ciudad}",
                fontSize = 12.sp,
                color = TextMuted,
                lineHeight = 18.sp
            )
            Spacer(Modifier.height(10.dp))
            // Estado + horario
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .background(statusDotColor, SaludifyRadius.full)
                    )
                    Text(
                        text = sucursal.statusLabel,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = statusTextColor
                    )
                }
                Text(text = "·", fontSize = 12.sp, color = TextPlaceholder)
                Text(
                    text = sucursal.horarioApertura ?: sucursal.horario,
                    fontSize = 12.sp,
                    color = if (sucursal.estaAbierto) TextMuted else TextPlaceholder
                )
            }
            Spacer(Modifier.height(12.dp))
            // Botones
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(SaludifyRadius.chip)
                        .background(BrandPrimarySurface, SaludifyRadius.chip)
                        .clickable {
                            val query = Uri.encode("${sucursal.direccion}, ${sucursal.ciudad}")
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$query"))
                            context.startActivity(intent)
                        }
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = BrandPrimary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Cómo llegar",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = BrandPrimary
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(SaludifyRadius.chip)
                        .background(BackgroundSubtle, SaludifyRadius.chip)
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${sucursal.telefono}"))
                            context.startActivity(intent)
                        }
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "Llamar",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}

// ── FAB Gemini ────────────────────────────────────────────────────────────────

@Composable
private fun HelpGeminiFab(modifier: Modifier = Modifier) {
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
