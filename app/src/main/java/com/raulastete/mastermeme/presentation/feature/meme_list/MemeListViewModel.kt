package com.raulastete.mastermeme.presentation.feature.meme_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemeListViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(MemeListUiState())
    val uiState = _uiState.asStateFlow()

    fun updateSearchTemplateQuery(query: String) {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(query = query)
        )
    }

    fun openTemplatesModal() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isOpen = true)
        )
    }

    fun dismissTemplatesModal() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(
                isOpen = false,
                isSearchBarDisplayed = false
            )
        )
    }

    fun activateSearchTemplate() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isSearchBarDisplayed = true)
        )
    }

    fun deactivateSearchTemplate() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isSearchBarDisplayed = false)
        )
    }

    fun cleanSearchTemplateQuery() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(query = "")
        )
    }

    fun exitSelectionMode() {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = false
        )
    }

    fun enterSelectionMode(firstMemeSelectedId: String) {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = false,
            memes = uiState.value.memes.map { meme ->
                if (meme.id == firstMemeSelectedId) {
                    meme.copy(isSelected = true)
                } else {
                    meme
                }
            }
        )
    }

    fun shareMemes() {

    }

    fun deleteMemes() {

    }

    fun toggleFavoriteState(memeId: String) {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = false,
            memes = uiState.value.memes.map { meme ->
                if (meme.id == memeId) {
                    meme.copy(isFavorite = meme.isFavorite.not())
                } else {
                    meme
                }
            }
        )
    }

    fun toggleSelectionState(memeId: String) {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = false,
            memes = uiState.value.memes.map { meme ->
                if (meme.id == memeId) {
                    meme.copy(isSelected = meme.isSelected.not())
                } else {
                    meme
                }
            }
        )
    }

}