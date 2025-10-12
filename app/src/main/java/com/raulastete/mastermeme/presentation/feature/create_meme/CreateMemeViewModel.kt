package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.lifecycle.ViewModel
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateMemeViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(CreateMemeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateFontSize(fontSize: Float) {
        _uiState.value = _uiState.value.copy(
            textState = _uiState.value.textState.copy(fontSize = fontSize)
        )
    }

    fun updateFontColor(fontColor: MemeFontColorUi) {
        _uiState.value = _uiState.value.copy(
            textState = _uiState.value.textState.copy(fontColor = fontColor)
        )

    }
}