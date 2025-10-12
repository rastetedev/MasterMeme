package com.raulastete.mastermeme.presentation.feature.meme_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MemeCard(
    modifier: Modifier = Modifier,
    imageUrl: String,
    isFavorite: Boolean,
    isSelected: Boolean,
    isSelectionMode: Boolean,
    onFavoriteClick: () -> Unit,
    onSelectionClick: () -> Unit,
    onLongSelectionClick: () -> Unit
) {
    Card(
        modifier = modifier
            .aspectRatio(1f) // Makes the card square
            .clip(MaterialTheme.shapes.medium)
            .combinedClickable(
                onClick = {
                    if (isSelectionMode) onSelectionClick()
                    else onFavoriteClick()
                },
                onLongClick = {
                    if (!isSelectionMode) onLongSelectionClick()
                }
            )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background Image
            AsyncImage(
                model = imageUrl,
                contentDescription = "Meme Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient Overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xCC141218)),
                            startY = 0.6f
                        )
                    )
            )

            // Conditional Buttons
            if (isSelectionMode) {
                SelectionToggleButton(
                    isSelected = isSelected,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                )
            } else {
                FavoriteToggleButton(
                    isFavorite = isFavorite,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
        }
    }
}

@Composable
private fun SelectionToggleButton(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(24.dp)
            .clip(CircleShape)
            .then(
                if (isSelected) {
                    Modifier.background(Color(0xFFEADDFF))
                } else {
                    Modifier.border(1.dp, Color.White, CircleShape)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Selected",
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun FavoriteToggleButton(
    isFavorite: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.padding(16.dp)
    ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
            tint = Color.White
        )
    }
}


// Previews

@Preview(showBackground = true, name = "Card - Normal State")
@Composable
private fun MemeCardNormalPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MemeCard(
                modifier = Modifier.size(176.dp),
                imageUrl = "",
                isFavorite = false,
                isSelected = false,
                isSelectionMode = false,
                onFavoriteClick = {},
                onSelectionClick = {},
                onLongSelectionClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Card - Favorite State")
@Composable
private fun MemeCardFavoritePreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MemeCard(
                modifier = Modifier.size(176.dp),
                imageUrl = "",
                isFavorite = true,
                isSelected = false,
                isSelectionMode = false,
                onFavoriteClick = {},
                onSelectionClick = {},
                onLongSelectionClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Card - Selection Mode (Not Selected)")
@Composable
private fun MemeCardSelectionModePreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MemeCard(
                modifier = Modifier.size(176.dp),
                imageUrl = "",
                isFavorite = false,
                isSelected = false,
                isSelectionMode = true,
                onFavoriteClick = {},
                onSelectionClick = {},
                onLongSelectionClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Card - Selection Mode (Selected)")
@Composable
private fun MemeCardSelectionModeSelectedPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            MemeCard(
                modifier = Modifier.size(176.dp),
                imageUrl = "",
                isFavorite = false,
                isSelected = true,
                isSelectionMode = true,
                onFavoriteClick = {},
                onSelectionClick = {},
                onLongSelectionClick = {}
            )
        }
    }
}
