package com.example.saludify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.saludify.ui.theme.BackgroundSurface
import com.example.saludify.ui.theme.BorderDefault
import com.example.saludify.ui.theme.BrandPrimary
import com.example.saludify.ui.theme.BrandPrimarySurface
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.TextDisabled
import com.example.saludify.ui.theme.TextPlaceholder

@Composable
fun BottomBar(
    selectedScreen: String,
    onScreenSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(BackgroundSurface)
            .navigationBarsPadding()
    ) {
        HorizontalDivider(color = BorderDefault, thickness = 1.dp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomBarItem(
                icon = Icons.Filled.Home,
                label = "Inicio",
                selected = selectedScreen == "home",
                onClick = { onScreenSelected("home") },
                modifier = Modifier.weight(1f)
            )
            BottomBarItem(
                icon = Icons.Filled.Add,
                label = "Atención",
                selected = selectedScreen == "attention",
                onClick = { onScreenSelected("attention") },
                modifier = Modifier.weight(1f)
            )
            BottomBarItem(
                icon = Icons.Filled.Menu,
                label = "Trámites",
                selected = selectedScreen == "procedures",
                onClick = { onScreenSelected("procedures") },
                modifier = Modifier.weight(1f)
            )
            BottomBarItem(
                icon = Icons.Filled.Help,
                label = "Ayuda",
                selected = selectedScreen == "help",
                onClick = { onScreenSelected("help") },
                modifier = Modifier.weight(1f)
            )
            BottomBarItem(
                icon = Icons.Filled.Person,
                label = "Perfil",
                selected = selectedScreen == "profile",
                onClick = { onScreenSelected("profile") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun BottomBarItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(width = 44.dp, height = 26.dp)
                    .clip(SaludifyRadius.badge)
                    .background(BrandPrimarySurface),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = BrandPrimary,
                    modifier = Modifier.size(16.dp)
                )
            }
        } else {
            Box(
                modifier = Modifier.size(width = 26.dp, height = 26.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = TextDisabled,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
            color = if (selected) BrandPrimary else TextPlaceholder,
            lineHeight = 1.em
        )
    }
}
