package com.raulastete.mastermeme.presentation.feature.meme_list

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raulastete.mastermeme.R
import com.raulastete.mastermeme.presentation.feature.meme_list.components.EmptyMemeListPlaceholder
import com.raulastete.mastermeme.presentation.feature.meme_list.components.MemeListGrid
import com.raulastete.mastermeme.presentation.feature.meme_list.components.MemeTemplateBottomSheet
import com.raulastete.mastermeme.presentation.ui.components.GradientFab
import com.raulastete.mastermeme.presentation.ui.components.NormalTopBar
import com.raulastete.mastermeme.presentation.ui.components.SelectionTopBar

@Composable
fun MemeListScreen(
    viewModel: MemeListViewModel = viewModel(),
    navigateToCreateMeme: (templateResource: Int) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MemeListScreenContent(
        uiState = uiState,
        navigateToCreateMeme = navigateToCreateMeme,
        onToggleMemeFavoriteState = viewModel::toggleFavoriteState,
        onOpenTemplatesModal = viewModel::openTemplatesModal,
        onDismissTemplatesModal = viewModel::dismissTemplatesModal,
        onActivateSearchTemplate = viewModel::activateSearchTemplate,
        onDeactivateSearchTemplate = viewModel::deactivateSearchTemplate,
        onCleanSearchTemplateQuery = viewModel::cleanSearchTemplateQuery,
        onSearchTemplateQueryChange = viewModel::updateSearchTemplateQuery,
        onToggleMemeSelectionState = viewModel::toggleSelectionState,
        onShareMemes = viewModel::shareMemes,
        onDeleteMemes = viewModel::deleteMemes,
        onExitSelectionMode = viewModel::exitSelectionMode,
        onEnterSelectionMode = viewModel::enterSelectionMode
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MemeListScreenContent(
    uiState: MemeListUiState,
    navigateToCreateMeme: (templateResource: Int) -> Unit,
    onToggleMemeFavoriteState: (memeId: String) -> Unit,
    onOpenTemplatesModal: () -> Unit,
    onDismissTemplatesModal: () -> Unit,
    onActivateSearchTemplate: () -> Unit,
    onDeactivateSearchTemplate: () -> Unit,
    onCleanSearchTemplateQuery: () -> Unit,
    onSearchTemplateQueryChange: (String) -> Unit,
    onToggleMemeSelectionState: (memeId: String) -> Unit,
    onShareMemes: () -> Unit,
    onDeleteMemes: () -> Unit,
    onEnterSelectionMode: (memeId: String) -> Unit,
    onExitSelectionMode: () -> Unit
) {

    Scaffold(
        topBar = {
            AnimatedContent(uiState.isInSelectionMode) { isSelectionMode ->
                if (isSelectionMode) {
                    SelectionTopBar(
                        selectedCount = uiState.selectedMemes.size,
                        onExitSelectionMode = onExitSelectionMode,
                        onShareClick = onShareMemes,
                        onDeleteClick = onDeleteMemes
                    )
                } else {
                    NormalTopBar(title = stringResource(R.string.meme_list_title))
                }
            }
        },
        floatingActionButton = {
            GradientFab(onClick = onOpenTemplatesModal)
        }
    ) { paddingValues ->
        when {
            uiState.memes.isEmpty() -> EmptyMemeListPlaceholder(paddingValues)

            else -> MemeListGrid(
                paddingValues,
                memes = uiState.memes,
                isSelectionMode = uiState.isInSelectionMode,
                onFavoriteClick = onToggleMemeFavoriteState,
                onSelectedClick = onToggleMemeSelectionState,
                onEnterSelectionMode = onEnterSelectionMode
            )
        }

        if (uiState.modalState.isOpen) {
            MemeTemplateBottomSheet(
                uiState = uiState,
                onOpenSearchTemplate = onActivateSearchTemplate,
                onCloseSearchTemplate = onDeactivateSearchTemplate,
                onCleanQuery = onCleanSearchTemplateQuery,
                onDismissModal = onDismissTemplatesModal,
                onQueryChange = onSearchTemplateQueryChange,
                navigateToCreateMeme = navigateToCreateMeme
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
            navigateToCreateMeme = {},
            onExitSelectionMode = {},
            onShareMemes = {},
            onDeleteMemes = {},
            onOpenTemplatesModal = {},
            onDismissTemplatesModal = {},
            onActivateSearchTemplate = {},
            onDeactivateSearchTemplate = {},
            onCleanSearchTemplateQuery = {},
            onSearchTemplateQueryChange = {},
            onToggleMemeFavoriteState = {},
            onToggleMemeSelectionState = {},
            onEnterSelectionMode = {}
        )
    }
}
