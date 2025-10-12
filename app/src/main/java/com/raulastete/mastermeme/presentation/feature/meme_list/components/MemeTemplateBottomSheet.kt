package com.raulastete.mastermeme.presentation.feature.meme_list.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raulastete.mastermeme.presentation.feature.meme_list.MemeListUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemeTemplateBottomSheet(
    uiState: MemeListUiState,
    onOpenSearchTemplate: () -> Unit,
    onCloseSearchTemplate: () -> Unit,
    onCleanQuery: () -> Unit,
    onDismissModal: () -> Unit,
    onQueryChange: (query: String) -> Unit,
    onSelectTemplate: (memeTemplateResource: Int) -> Unit
){
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars),
        onDismissRequest = onDismissModal,
        sheetState = sheetState,
        contentWindowInsets = {
            BottomSheetDefaults.windowInsets
                .add(WindowInsets(left = 16.dp, right = 16.dp))
        }
    ) {
        AnimatedContent(uiState.templatesModalState.isSearchBarDisplayed) { isSearchBarDisplayed ->
            if (isSearchBarDisplayed) {
                SearchTemplateMode(
                    query = uiState.templatesModalState.query,
                    onQueryChange = onQueryChange,
                    onCloseSearchTemplate = onCloseSearchTemplate,
                    onCleanQuery = onCleanQuery,
                    templatesQuantity = uiState.templatesModalState.templates.size
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
            items(uiState.templatesModalState.templates) {
                MemeTemplateCard(it) {
                    onSelectTemplate(it)
                }
            }
        }
    }
}