package com.raulastete.mastermeme.feature.meme_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.raulastete.mastermeme.feature.meme_list.components.EmptyMemeListContent
import com.raulastete.mastermeme.feature.meme_list.components.MemeListGrid
import com.raulastete.mastermeme.feature.meme_list.components.ModalDefaultHeader
import com.raulastete.mastermeme.feature.meme_list.components.SearchTemplateMode
import com.raulastete.mastermeme.ui.components.GradientFab
import com.raulastete.mastermeme.ui.components.NormalTopBar
import com.raulastete.mastermeme.ui.components.SelectionTopBar

@Composable
fun MemeListScreen(
    viewModel: MemeListViewModel = viewModel(),
    navigateToCreateMeme: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MemeListScreenContent(
        uiState = uiState,
        navigateToCreateMeme = navigateToCreateMeme,
        onOpenModal = viewModel::openModal,
        onDismissModal = viewModel::dismissModal,
        onOpenSearchTemplate = viewModel::openSearchTemplate,
        onCloseSearchTemplate = viewModel::closeSearchTemplate,
        onQueryChange = viewModel::updateQuery
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MemeListScreenContent(
    uiState: MemeListUiState,
    navigateToCreateMeme: () -> Unit,
    onOpenModal: () -> Unit,
    onDismissModal: () -> Unit,
    onOpenSearchTemplate: () -> Unit,
    onCloseSearchTemplate: () -> Unit,
    onQueryChange: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()

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
            GradientFab(onClick = onOpenModal)
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

        if (uiState.modalState.isOpen) {
            ModalBottomSheet(
                modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
                onDismissRequest = onDismissModal,
                sheetState = sheetState,
                contentWindowInsets = {
                    BottomSheetDefaults.windowInsets
                        .add(
                            WindowInsets(left = 16.dp, right = 16.dp)
                        )
                }
            ) {
                AnimatedContent(uiState.modalState.isSearchMode) { target ->
                    if (target) {
                        SearchTemplateMode(
                            query = uiState.modalState.query,
                            onQueryChange = onQueryChange,
                            onCloseSearchTemplate = onCloseSearchTemplate,
                            templatesQuantity = uiState.modalState.templates.size
                        )
                    } else {
                        ModalDefaultHeader(
                            onOpenSearchTemplate = onOpenSearchTemplate
                        )
                    }
                }

                Spacer(Modifier.height(40.dp))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(uiState.modalState.templates) {
                        Box(
                            Modifier
                                .aspectRatio(1f)
                                .clip(RoundedCornerShape(8.dp))
                        ) {
                            AsyncImage(
                                model = it,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MemeListScreenContentPreview() {
    MaterialTheme {
        MemeListScreenContent(
            uiState = MemeListUiState(),
            navigateToCreateMeme = {},
            onOpenModal = {},
            onDismissModal = {},
            onOpenSearchTemplate = {},
            onCloseSearchTemplate = {},
            onQueryChange = {}
        )
    }
}
