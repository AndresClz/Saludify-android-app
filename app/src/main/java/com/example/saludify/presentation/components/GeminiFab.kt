package com.example.saludify.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.saludify.ui.theme.SaludifyRadius
import com.example.saludify.ui.theme.TextOnPrimary
import kotlin.math.roundToInt

@Composable
fun DraggableGeminiFab() {
    val fabSize = 52.dp
    val margin = 16.dp
    val density = LocalDensity.current

    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var fabOffset by remember { mutableStateOf(Offset.Unspecified) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size ->
                if (containerSize == IntSize.Zero) {
                    val fabSizePx = with(density) { fabSize.toPx() }
                    val marginPx = with(density) { margin.toPx() }
                    fabOffset = Offset(
                        x = size.width - fabSizePx - marginPx,
                        y = size.height - fabSizePx - marginPx
                    )
                }
                containerSize = size
            }
    ) {
        if (fabOffset != Offset.Unspecified) {
            val fabSizePx = with(density) { fabSize.toPx() }

            Box(
                modifier = Modifier
                    .offset { IntOffset(fabOffset.x.roundToInt(), fabOffset.y.roundToInt()) }
                    .size(fabSize)
                    .pointerInput(Unit) {
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            fabOffset = Offset(
                                x = (fabOffset.x + dragAmount.x)
                                    .coerceIn(0f, containerSize.width - fabSizePx),
                                y = (fabOffset.y + dragAmount.y)
                                    .coerceIn(0f, containerSize.height - fabSizePx)
                            )
                        }
                    }
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
    }
}
