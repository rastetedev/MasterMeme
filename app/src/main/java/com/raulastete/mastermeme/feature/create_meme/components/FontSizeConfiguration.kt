package com.raulastete.mastermeme.feature.create_meme.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.ui.theme.White
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FontSizeConfiguration(
    modifier: Modifier = Modifier,
    fontSizeFloat: Float = 0.5f,
    onFontSizeFloatChange: (Float) -> Unit

) {
    val interactionSource = remember { MutableInteractionSource() }
    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collectLatest { interaction ->
            when (interaction) {
                is DragInteraction.Start -> isDragging = true
                is DragInteraction.Stop -> isDragging = false
                is DragInteraction.Cancel -> isDragging = false
            }
        }
    }
    val sliderColor = MaterialTheme.colorScheme.secondaryFixedDim

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text("Aa", style = MaterialTheme.typography.bodySmall, color = White)

        Slider(
            modifier = Modifier.weight(1f),
            value = fontSizeFloat,
            onValueChange = onFontSizeFloatChange,
            interactionSource = interactionSource,
            colors = SliderDefaults.colors(
                activeTrackColor = MaterialTheme.colorScheme.secondaryFixedDim,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryFixedDim,
                thumbColor = MaterialTheme.colorScheme.secondaryFixedDim
            ),
            thumb = {

                val animatedOuterRadius by animateDpAsState(
                    targetValue = if (isDragging) 16.dp else 12.8.dp, //Values for 32dp
                    label = "SliderThumbOuterRadius"
                )

                Canvas(
                    modifier = Modifier.size(32.dp),
                    onDraw = {
                        // Draw outer circle
                        drawCircle(
                            color = sliderColor.copy(alpha = 0.25f),
                            radius =animatedOuterRadius.toPx()
                        )

                        // Draw inner circle
                        drawCircle(
                            color = sliderColor,
                            radius = size.width / 4
                        )
                    }
                )
            },
            track = {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                ) {
                    val trackStrokeWidth = 1.dp.toPx()
                    val trackCornerRadius = 1.dp.toPx()

                    drawRoundRect(
                        color = sliderColor,
                        size = size,
                        cornerRadius = CornerRadius(trackCornerRadius),
                        style = Stroke(width = trackStrokeWidth)
                    )
                }
            }
        )

        Text("Aa", style = MaterialTheme.typography.headlineLarge, color = White)
    }
}