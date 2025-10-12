package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.lifecycle.ViewModel
import com.raulastete.mastermeme.presentation.feature.create_meme.components.TextOption
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateMemeViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(CreateMemeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateFontSize(fontSize: Float, textId: String) {
        _uiState.value = _uiState.value.copy(
            textStates = uiState.value.textStates.map { textState ->
                if (textState.uuid == textId) {
                    textState.copy(fontSize = fontSize)
                } else {
                    textState
                }
            }
        )
    }

    fun updateFontColor(fontColor: MemeFontColorUi, textId: String) {
        _uiState.value = _uiState.value.copy(
            textStates = uiState.value.textStates.map { textState ->
                if (textState.uuid == textId) {
                    textState.copy(fontColor = fontColor)
                } else {
                    textState
                }
            }
        )
    }

    fun updateFontType(fontType: MemeFontTypeUi, textId: String) {
        _uiState.value = _uiState.value.copy(
            textStates = uiState.value.textStates.map { textState ->
                if (textState.uuid == textId) {
                    textState.copy(fontType = fontType)
                } else {
                    textState
                }
            }
        )
    }

    fun addNewTextBox() {
        _uiState.value = _uiState.value.copy(
            textStates = uiState.value.textStates + TextState(),
            editModeState = EditModeState(
                mode = EditMode.InEdition(textOption = null)
            )
        )
    }

    fun selectEditFontTypeOption(){
        _uiState.value = _uiState.value.copy(
            editModeState = EditModeState(
                mode = EditMode.InEdition(textOption = TextOption.FontType)
            )
        )
    }

    fun selectEditFontColorOption(){
        _uiState.value = _uiState.value.copy(
            editModeState = EditModeState(
                mode = EditMode.InEdition(textOption = TextOption.FontColor)
            )
        )
    }

    fun selectEditFontSizeOption(){
        _uiState.value = _uiState.value.copy(
            editModeState = EditModeState(
                mode = EditMode.InEdition(textOption = TextOption.FontSize)
            )
        )
    }
}