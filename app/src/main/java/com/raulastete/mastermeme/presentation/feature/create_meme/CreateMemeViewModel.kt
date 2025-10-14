package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.lifecycle.ViewModel
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateMemeViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(CreateMemeUiState())
    val uiState = _uiState.asStateFlow()

    fun updateFontSize(fontSize: Float, textId: String) {
        _uiState.value = _uiState.value.copy(
            memeTextStates = uiState.value.memeTextStates.map { textState ->
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
            memeTextStates = uiState.value.memeTextStates.map { textState ->
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
            memeTextStates = uiState.value.memeTextStates.map { textState ->
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
            memeTextStates = uiState.value.memeTextStates + MemeTextState(),
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.InEdition(currentTextOption = null)
            )
        )
    }

    fun selectEditFontTypeOption(){
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.InEdition(currentTextOption = TextOption.FontType)
            )
        )
    }

    fun selectEditFontColorOption(){
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.InEdition(currentTextOption = TextOption.FontColor)
            )
        )
    }

    fun selectEditFontSizeOption(){
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.InEdition(currentTextOption = TextOption.FontSize)
            )
        )
    }

    //TODO: Implement with real logic later
    fun discardLatestEdition(){
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.OnHold
            )
        )
    }

    //TODO: Implement with real logic later
    fun confirmLatestEdition(){
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = EditMode.OnHold
            )
        )
    }
}