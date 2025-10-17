package com.raulastete.mastermeme.presentation.feature.create_meme

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.lifecycle.ViewModel
import com.raulastete.mastermeme.core.MemeTextUndoRedoManager
import com.raulastete.mastermeme.presentation.model.MemeFontColorUi
import com.raulastete.mastermeme.presentation.model.MemeFontTypeUi
import com.raulastete.mastermeme.presentation.model.TextConfigurationOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreateMemeViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(CreateMemeUiState())
    val uiState = _uiState.asStateFlow()

    private val memeTextUndoRedoManager = MemeTextUndoRedoManager()

    fun updateFontSize(fontSize: Float) {
        val textInEditionId = uiState.value.editionSource?.textId

        _uiState.value = _uiState.value.copy(
            memeTextStates = uiState.value.memeTextStates.map { textState ->
                if (textState.uuid == textInEditionId) {
                    textState.copy(fontSize = fontSize)
                } else {
                    textState
                }
            }
        )
    }

    fun updateFontColor(fontColor: MemeFontColorUi) {
        val textInEditionId = uiState.value.editionSource?.textId

        _uiState.value = _uiState.value.copy(
            memeTextStates = uiState.value.memeTextStates.map { textState ->
                if (textState.uuid == textInEditionId) {
                    textState.copy(fontColor = fontColor)
                } else {
                    textState
                }
            }
        )
    }

    fun updateFontType(fontType: MemeFontTypeUi) {
        val textInEditionId = uiState.value.editionSource?.textId

        _uiState.value = _uiState.value.copy(
            memeTextStates = uiState.value.memeTextStates.map { textState ->
                if (textState.uuid == textInEditionId) {
                    textState.copy(fontType = fontType)
                } else {
                    textState
                }
            }
        )
    }

    fun addMemeText() {
        //TODO: Pendent implementation
    }

    fun finishEditingText() {
        //TODO: Pendent implementation
    }

    fun setImageBounds(offset: Offset, size: Size) {
        _uiState.value = _uiState.value.copy(
            initialData = uiState.value.initialData.copy(
                imageBounds = ImageBounds(
                    offset = offset,
                    size = size
                )
            )
        )
    }

    fun selectEditFontTypeOption() {
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = (uiState.value.editionButtonsState.editMode as EditionButtonsState.EditMode.Active).copy(
                    currentTextOption = TextConfigurationOption.FontType
                )
            )
        )
    }

    fun selectEditFontColorOption() {
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = (uiState.value.editionButtonsState.editMode as EditionButtonsState.EditMode.Active).copy(
                    currentTextOption = TextConfigurationOption.FontColor
                )
            )
        )
    }

    fun selectEditFontSizeOption() {
        _uiState.value = _uiState.value.copy(
            editionButtonsState = EditionButtonsState(
                editMode = (uiState.value.editionButtonsState.editMode as EditionButtonsState.EditMode.Active).copy(
                    currentTextOption = TextConfigurationOption.FontSize
                )
            )
        )
    }

    fun discardLatestEdition() {
        val isNewText = uiState.value.isNewText
        val editionSource = uiState.value.editionSource

        if (isNewText) removeTextFromMeme(editionSource?.textId)
        else restoreExistingTextToPreviousVersion(editionSource)

        restoreButtonsToHoldState()
    }

    fun confirmLatestEdition() {
        pushChangeToUndoRedoManager()
        restoreButtonsToHoldState()
    }

    private fun pushChangeToUndoRedoManager() {
        val confirmedMemeText =
            uiState.value.memeTextStates.first { it.uuid == uiState.value.editionSource?.textId }

        memeTextUndoRedoManager.addNewChange(confirmedMemeText)
    }


    private fun removeTextFromMeme(textIdToRemove: String?) {
        _uiState.update {
            it.copy(
                memeTextStates = it.memeTextStates.filter { memeText ->
                    memeText.uuid != textIdToRemove
                }
            )
        }
    }

    private fun restoreExistingTextToPreviousVersion(editionSource: EditionSource?) {

        val existingTextSource = editionSource as? EditionSource.ExistingText ?: return
        val textIdToRestore = existingTextSource.textId
        val backup = existingTextSource.backupForUndo

        _uiState.update {
            it.copy(
                memeTextStates = it.memeTextStates.map { memeText ->
                    if (memeText.uuid == textIdToRestore) {
                        backup
                    } else {
                        memeText
                    }
                }
            )
        }
    }

    private fun restoreButtonsToHoldState() {
        _uiState.update {
            it.copy(
                editionSource = null,
                editionButtonsState = EditionButtonsState(
                    editMode = EditionButtonsState.EditMode.OnHold(
                        canUndo = memeTextUndoRedoManager.canUndo,
                        canRedo = memeTextUndoRedoManager.canRedo
                    ),
                ),
            )
        }
    }
}