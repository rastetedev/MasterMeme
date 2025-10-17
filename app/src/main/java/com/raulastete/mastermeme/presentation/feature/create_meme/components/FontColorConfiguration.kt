package com.raulastete.mastermeme.presentation.feature.create_meme.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi

@Composable
fun FontColorConfiguration(
    modifier: Modifier = Modifier,
    selectedColor: MemeFontColorUi?,
    fontColorList: List<MemeFontColorUi>,
    onColorSelected: (MemeFontColorUi) -> Unit
) {
    Box(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp)
            .padding(bottom = 2.dp, top = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            items(fontColorList) { memeFontColor ->
                val isSelected = memeFontColor == selectedColor

                ColorCircle(
                    memeFontColor = memeFontColor,
                    isSelected = isSelected,
                    onClick = { onColorSelected(memeFontColor) }
                )
            }
        }
    }
}

@Composable
private fun ColorCircle(
    memeFontColor: MemeFontColorUi,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val size = 32.dp
    val selectionRingSize = size * 1.4f
    val selectionRingColor = Color(0x33FFFFFF)

    val animatedRingRadius by animateDpAsState(
        targetValue = if (isSelected) selectionRingSize / 2 else 0.dp,
        label = "RingRadius"
    )

    val animatedRingAlpha by animateFloatAsState(
        targetValue = if (isSelected) 0.2f else 0f,
        label = "RingAlpha"
    )

    Canvas(
        modifier = modifier
            .size(selectionRingSize)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(bounded = false, radius = selectionRingSize / 2),
                onClick = onClick
            )
    ) {

        drawCircle(
            color = selectionRingColor.copy(alpha = animatedRingAlpha),
            radius = animatedRingRadius.toPx(),
            center = center
        )

        drawCircle(
            color = memeFontColor.color,
            radius = size.toPx() / 2,
            center = center
        )
    }
}