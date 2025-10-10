package com.raulastete.mastermeme.feature.meme_list

data class MemeListUiState(
    val memes: List<MemeUiState> = emptyList(),
    val isSelectionMode: Boolean = false,
) {
    val selectedMemes = memes.filter { it.isSelected }
}

data class MemeUiState(
    val id: String = "",
    val image: String = "",
    val isFavorite: Boolean = false,
    val isSelected: Boolean = false,
)