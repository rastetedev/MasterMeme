package com.raulastete.mastermeme.presentation.feature.create_meme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.ui.theme.ButtonGradientDefault

@Composable
fun MainOptions(
    modifier: Modifier = Modifier,
    onUndoEdition: () -> Unit,
    onRedoEdition: () -> Unit,
    onAddTextBox: () -> Unit,
    onSaveMeme: () -> Unit
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            IconButton(onClick = onUndoEdition) {
                Icon(
                    ImageVector.vectorResource(R.drawable.undo),
                    contentDescription = null,
                    modifier = Modifier.size(44.dp)
                )
            }
            Spacer(Modifier.width(12.dp))
            IconButton(onClick = onRedoEdition) {
                Icon(
                    ImageVector.vectorResource(R.drawable.redo),
                    contentDescription = null,
                    modifier = Modifier.size(44.dp)
                )
            }
        }
        Box(
            modifier = Modifier
                .drawBehind {
                    drawRoundRect(
                        brush = ButtonGradientDefault,
                        cornerRadius = CornerRadius(8.dp.toPx()),
                        style = Stroke(width = 1.dp.toPx())
                    )
                }
                .clip(RoundedCornerShape(8.dp))
                .clickable(onClick = onAddTextBox)
                .padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add text",
                style = MaterialTheme.typography.labelLarge.copy(
                    brush = ButtonGradientDefault
                )
            )
        }

        Box(
            Modifier
                .clickable { onSaveMeme() }
                .background(
                    ButtonGradientDefault,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 16.dp, vertical = 14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Save meme",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            )
        }

    }
}