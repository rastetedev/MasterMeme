package com.raulastete.mastermeme.feature.create_meme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.ui.theme.White

enum class TextOption {
    FontFamily,
    FontSize,
    FontColor
}

@Composable
fun TextOptions(
    modifier: Modifier = Modifier,
    optionSelected: TextOption?,
    onDiscardChanges: () -> Unit,
    onConfirmChanges: () -> Unit,
    onEditFontFamily: () -> Unit,
    onEditFontSize: () -> Unit,
    onEditFontColor: () -> Unit
) {

    Row(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(onClick = onDiscardChanges) {
            Icon(
                ImageVector.vectorResource(R.drawable.close),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondaryFixedDim,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(Modifier.weight(1f))

        Row(horizontalArrangement = Arrangement.Center) {

            TextOptionButton(
                onClick = onEditFontFamily,
                selected = optionSelected == TextOption.FontFamily
            ) {
                Icon(
                    ImageVector.vectorResource(R.drawable.text_style),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            TextOptionButton(
                onClick = onEditFontSize,
                selected = optionSelected == TextOption.FontSize
            ) {
                Icon(
                    ImageVector.vectorResource(R.drawable.text_size),
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.width(16.dp))

            TextOptionButton(
                onClick = onEditFontColor,
                selected = optionSelected == TextOption.FontColor
            ) {
                Icon(
                    painter = painterResource(R.drawable.text_color),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            }
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = onConfirmChanges) {
            Icon(
                ImageVector.vectorResource(R.drawable.done),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondaryFixedDim,
                modifier = Modifier.size(24.dp)
            )
        }

    }
}

@Composable
private fun TextOptionButton(
    onClick: () -> Unit,
    selected: Boolean,
    icon: @Composable () -> Unit

) {
    FilledIconButton(
        modifier = Modifier.size(48.dp),
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = IconButtonDefaults.filledIconButtonColors(
            containerColor = if (selected) MaterialTheme.colorScheme.surfaceContainerHigh else Color.Transparent,
        )
    ) {
        icon()
    }
}