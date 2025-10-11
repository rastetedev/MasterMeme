package com.raulastete.mastermeme.feature.meme_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemeListViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(MemeListUiState())
    val uiState = _uiState.asStateFlow()

    fun updateQuery(query: String) {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(query = query)
        )
    }

    fun openModal() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isOpen = true)
        )
    }

    fun dismissModal() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isOpen = false)
        )
    }

    fun openSearchTemplate() {
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isSearchMode = true)
        )
    }

    fun closeSearchTemplate(){
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(isSearchMode = false)
        )
    }

    fun cleanQuery(){
        _uiState.value = _uiState.value.copy(
            modalState = _uiState.value.modalState.copy(query = "")
        )
    }

}