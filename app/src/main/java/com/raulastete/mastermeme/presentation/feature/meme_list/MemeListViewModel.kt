package com.raulastete.mastermeme.presentation.feature.meme_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MemeListViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(MemeListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchMemes()
    }

    private fun fetchMemes() {
        viewModelScope.launch {
            delay(500)
            _uiState.value = _uiState.value.copy(
                memeListState = uiState.value.memeListState.copy(
                    memes = (1..20).map {
                        MemeUiState(
                            id = it.toString(),
                            image = "",
                            isFavorite = false,
                            isSelected = false
                        )
                    }
                )
            )
        }
    }

    fun updateSearchTemplateQuery(query: String) {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(query = query)
        )
    }

    fun openTemplatesModal() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(isOpen = true)
        )
    }

    fun selectMemeTemplate(memeTemplateResource: Int) {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(
                templateSelectedId = memeTemplateResource,
                isSearchBarDisplayed = false,
                isOpen = false
            )
        )
    }

    fun onNavigationHandled() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(
                templateSelectedId = null
            )
        )
    }

    fun dismissTemplatesModal() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(
                isOpen = false,
                isSearchBarDisplayed = false
            )
        )
    }

    fun activateSearchTemplate() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(isSearchBarDisplayed = true)
        )
    }

    fun deactivateSearchTemplate() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(isSearchBarDisplayed = false)
        )
    }

    fun cleanSearchTemplateQuery() {
        _uiState.value = _uiState.value.copy(
            templatesModalState = _uiState.value.templatesModalState.copy(query = "")
        )
    }

    fun exitSelectionMode() {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = false,
            memeListState = uiState.value.memeListState.copy(
                memes = uiState.value.memeListState.memes.map { meme ->
                    meme.copy(isSelected = false)
                }
            )
        )
    }

    fun enterSelectionMode(firstMemeSelectedId: String) {
        _uiState.value = _uiState.value.copy(
            isInSelectionMode = true,
            memeListState = uiState.value.memeListState.copy(
                memes = uiState.value.memeListState.memes.map { meme ->
                    if (meme.id == firstMemeSelectedId) {
                        meme.copy(isSelected = true)
                    } else {
                        meme
                    }
                }
            )
        )
    }

    fun updateSortingMemesMode(sortingMode: SortingMode) {
        _uiState.value = _uiState.value.copy(
            memeListState = uiState.value.memeListState.copy(sortingMode = sortingMode)
        )
    }

    fun shareMemes() {

    }

    fun deleteMemes() {

    }

    fun toggleFavoriteState(memeId: String) {
        _uiState.value = _uiState.value.copy(
            memeListState = uiState.value.memeListState.copy(
                memes = uiState.value.memeListState.memes.map { meme ->
                    if (meme.id == memeId) {
                        meme.copy(isFavorite = meme.isFavorite.not())
                    } else {
                        meme
                    }
                }
            )
        )
    }

    fun toggleSelectionState(memeId: String) {
        _uiState.value = _uiState.value.copy(
            memeListState = uiState.value.memeListState.copy(
                memes = uiState.value.memeListState.memes.map { meme ->
                    if (meme.id == memeId) {
                        meme.copy(isSelected = meme.isSelected.not())
                    } else {
                        meme
                    }
                }
            )
        )
    }
}