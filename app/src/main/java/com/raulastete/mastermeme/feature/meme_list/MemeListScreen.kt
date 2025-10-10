package com.raulastete.mastermeme.feature.meme_list

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.raulastete.mastermeme.feature.meme_list.components.EmptyMemeListContent
import com.raulastete.mastermeme.feature.meme_list.components.MemeListGrid
import com.raulastete.mastermeme.ui.components.GradientFab
import com.raulastete.mastermeme.ui.components.NormalTopBar
import com.raulastete.mastermeme.ui.components.SelectionTopBar

@Composable
fun MemeListScreen(navigateToCreateMeme: () -> Unit) {
    val memeListUiState = MemeListUiState()
    MemeListScreenContent(
        uiState = memeListUiState,
        navigateToCreateMeme = navigateToCreateMeme
    )
}

@Composable
private fun MemeListScreenContent(
    uiState: MemeListUiState,
    navigateToCreateMeme: () -> Unit
) {
    Scaffold(
        topBar = {
            if (uiState.isSelectionMode)
                SelectionTopBar(
                    selectedCount = uiState.selectedMemes.size,
                    onExitSelectionMode = {},
                    onShareClick = {},
                    onDeleteClick = {}
                )
            else NormalTopBar(title = "Your memes")
        },
        floatingActionButton = {
            GradientFab {}
        }
    ) { paddingValues ->
        when {
            uiState.memes.isEmpty() -> EmptyMemeListContent(paddingValues)
            else -> MemeListGrid(
                paddingValues,
                memes = uiState.memes,
                isSelectionMode = uiState.isSelectionMode,
                onFavoriteClick = {},
                onSelectedClick = {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MemeListScreenContentPreview() {
    MaterialTheme {
        MemeListScreenContent(
            uiState = MemeListUiState(),
            navigateToCreateMeme = {}
        )
    }
}
