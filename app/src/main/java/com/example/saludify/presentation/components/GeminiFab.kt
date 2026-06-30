package com.example.saludify.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun DraggableGeminiFab() {
    val fabSize = 52.dp
    val margin = 16.dp
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    var containerSize by remember { mutableStateOf(IntSize.Zero) }
    var initialized by remember { mutableStateOf(false) }

    val fabX = remember { Animatable(0f) }
    var fabY by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onSizeChanged { size ->
                containerSize = size
                if (!initialized) {
                    val fabSizePx = with(density) { fabSize.toPx() }
                    val marginPx = with(density) { margin.toPx() }
                    scope.launch { fabX.snapTo(size.width - fabSizePx - marginPx) }
                    fabY = size.height - fabSizePx - marginPx
                    initialized = true
                }
            }
    ) {
        if (initialized) {
            val fabSizePx = with(density) { fabSize.toPx() }
            val marginPx = with(density) { margin.toPx() }

            Box(
                modifier = Modifier
                    .offset { IntOffset(fabX.value.roundToInt(), fabY.roundToInt()) }
                    .size(fabSize)
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, dragAmount ->
                                change.consume()
                                scope.launch {
                                    fabX.snapTo(
                                        (fabX.value + dragAmount.x)
                                            .coerceIn(0f, containerSize.width - fabSizePx)
                                    )
                                }
                                fabY = (fabY + dragAmount.y)
                                    .coerceIn(0f, containerSize.height - fabSizePx)
                            },
                            onDragEnd = {
                                val snapToRight = fabX.value > containerSize.width / 2f
                                val targetX = if (snapToRight)
                                    containerSize.width - fabSizePx - marginPx
                                else
                                    marginPx
                                scope.launch {
                                    fabX.animateTo(
                                        targetX,
                                        animationSpec = spring(
                                            dampingRatio = Spring.DampingRatioMediumBouncy,
                                            stiffness = Spring.StiffnessMedium
                                        )
                                    )
                                }
                            }
                        )
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
