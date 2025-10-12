package com.raulastete.mastermeme.presentation.feature.meme_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.presentation.feature.meme_list.MemeUiState

@Composable
fun MemeListGrid(
    paddingValues: PaddingValues,
    memes: List<MemeUiState>,
    isSelectionMode: Boolean,
    onFavoriteClick: (memeId: String) -> Unit,
    onSelectedClick: (memeId: String) -> Unit,
    onEnterSelectionMode: (memeId: String) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(memes, key = { it.id }) {
            MemeCard(
                imageUrl = it.image,
                isFavorite = it.isFavorite,
                isSelected = it.isSelected,
                isSelectionMode = isSelectionMode,
                onFavoriteClick = {
                    onFavoriteClick(it.id)
                },
                onSelectionClick = {
                    onSelectedClick(it.id)
                },
                onLongSelectionClick = {
                    onEnterSelectionMode(it.id)
                }
            )
        }
    }
}